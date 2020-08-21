package com.yinn.ymall.order.service.impl;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.common.constant.MQConstant;
import com.yinn.ymall.order.constant.OrderSettingConstant;
import com.yinn.ymall.order.constant.OrderStatusEnum;
import com.yinn.ymall.order.constant.RedisConstant;
import com.yinn.ymall.order.dao.OrderDao;
import com.yinn.ymall.order.dto.OrderConfirmDTO;
import com.yinn.ymall.order.dto.OrderPaidDTO;
import com.yinn.ymall.order.dto.OrderSubmitDTO;
import com.yinn.ymall.order.dto.PaymentInfoDTO;
import com.yinn.ymall.order.dto.PrePaymentDTO;
import com.yinn.ymall.order.dto.SkuLockDTO;
import com.yinn.ymall.order.entity.Order;
import com.yinn.ymall.order.entity.OrderItem;
import com.yinn.ymall.order.entity.PaymentInfo;
import com.yinn.ymall.order.exception.OrderPaymentException;
import com.yinn.ymall.order.exception.OrderSubmitException;
import com.yinn.ymall.order.feign.CartFeignService;
import com.yinn.ymall.order.feign.MemberFeignService;
import com.yinn.ymall.order.feign.PaymentFeignService;
import com.yinn.ymall.order.feign.WareFeignService;
import com.yinn.ymall.order.service.OrderItemService;
import com.yinn.ymall.order.service.OrderService;
import com.yinn.ymall.order.service.PaymentInfoService;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private PaymentInfoService paymentInfoService;

    @Autowired
    private PaymentFeignService paymentFeignService;

    @Autowired
    private CartFeignService cartFeignService;

    @Autowired
    private WareFeignService wareFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RocketMQTemplate rocketTemplate;

    @Override
    public OrderConfirmDTO getOrderConfirmDTO(Long memberId) {
        var orderConfirmDTO = new OrderConfirmDTO();
        // 远程查询收货地址列表
        var addressList = memberFeignService.listMemberAddressesByMemberId(memberId).getData();
        orderConfirmDTO.setAddressList(addressList);
        // 远程查询已选中商品项列表
        orderConfirmDTO.setCartItemList(cartFeignService.listSelectedCartItem().getData());
        // 设置防重令牌
        var token = setOrderToken(memberId);
        orderConfirmDTO.setOrderToken(token);
        return orderConfirmDTO;
    }

    @Override
    public Order getByOrderSn(String orderSn) {
        return getOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderSn, orderSn));
    }

    @Transactional
    @Override
    public void submit(OrderSubmitDTO orderSubmitDTO) {
        // 验证令牌
        if (!verifyToken(orderSubmitDTO.getMemberId(), orderSubmitDTO.getOrderToken()))
            throw new OrderSubmitException("订单令牌校验错误");
        // 获得订单和订单项并设置订单价格
        var order = getOrder(orderSubmitDTO);
        var orderItemList = getOrderItemList(orderSubmitDTO, order.getOrderSn());
        setOrderPrice(order, orderItemList);
        // 保存数据到数据库
        save(order);
        orderItemService.saveBatch(orderItemList);
        // 库存锁定
        skuLock(orderItemList, order);
        // 发送自动关单消息
        sendOrderCloseDelayMsg(order.getOrderSn());
    }

    @Override
    public void closeOrder(String orderSn) {
        // @formatter:off
        update(
            Wrappers.<Order>lambdaUpdate()
                            .set(Order::getStatus, OrderStatusEnum.CLOSED)
                            .eq(Order::getOrderSn, orderSn)
                            .eq(Order::getStatus, OrderStatusEnum.PENDING_PAYMENT)
            );
        // @formatter:on
        sendOrderCloseMsg(orderSn);
    }

    @Override
    public PrePaymentDTO wxMiniPay(String orderSn, String openId) {
        var order = getByOrderSn(orderSn);
        if (order == null || order.getStatus() != OrderStatusEnum.PENDING_PAYMENT)
            throw new OrderPaymentException();
        // TODO 为啥要进行order->paymentInfo->paymentInfoDTO的转换，不能直接一步到位吗？
        var paymentInfo = getPaymentInfo(order);
        var paymentInfoDTO = PaymentInfoDTO.convertFrom(paymentInfo).setOpenId(openId);
        paymentInfoService.save(paymentInfo);
        return paymentFeignService.wxMiniPay(paymentInfoDTO).getData();// TODO 远程调用出错处理
    }

    @Override
    public Order getByOrderStatus(Long userId, OrderStatusEnum orderStatus) {
        return getOne(Wrappers.<Order>lambdaQuery().eq(Order::getStatus, orderStatus).eq(Order::getMemberId, userId));
    }

    @Override
    public void orderPaid(OrderPaidDTO orderPaidDTO) {
        var orderSn = orderPaidDTO.getOrderSn();
        var paymentInfo = paymentInfoService.getByOrderSn(orderSn);
        var order = getByOrderSn(orderSn);
        if (!paymentInfo.getPayAmount().equals(orderPaidDTO.getPayAmount()))// 验价
            throw new OrderPaymentException();
        // 更改订单状态
        order.setStatus(OrderStatusEnum.PENDING_SHIPPED);
        save(order);
        // 更改支付状态
        paymentInfo.setCallbackTime(LocalDateTime.now());
        paymentInfoService.save(paymentInfo);
    }

    /**
     * 令牌的比对、删除必须是原子操作，故而使用Lua脚本
     *
     * @param memberId 会员id
     * @param token    订单令牌
     * @Date: 2020-05-21 14:03:58
     */
    private boolean verifyToken(Long memberId, String token) {
        String key = RedisConstant.ORDER_TOKEN_PRE + memberId;
        var script = new DefaultRedisScript<Long>(RedisConstant.LUA_SCRIPT, Long.class);
        return redisTemplate.execute(script, Collections.singletonList(key), token) == 1;
    }

    private String setOrderToken(Long memberId) {
        var token = UUID.randomUUID().toString().replace("-", "");
        String key = RedisConstant.ORDER_TOKEN_PRE + memberId;
        redisTemplate.opsForValue().set(key, token, RedisConstant.ORDER_TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        return token;
    }

    private Order getOrder(OrderSubmitDTO orderSubmitDTO) {
        var order = orderSubmitDTO.convertToOrder();
        var memberAddressId = orderSubmitDTO.getMemberAddressId();
        setBasicInfo(order);
        setFreightAmount(order, memberAddressId);
        setReceiverInfo(order, memberAddressId);
        return order;
    }

    /**
     * 设置订单的基本信息
     *
     * @Date: 2020-05-25 20:20:02
     */
    private void setBasicInfo(Order order) {
        // @formatter:off
        order.setOrderSn(IdWorker.getIdStr())
                    .setAutoConfirmTime(OrderSettingConstant.AUTO_CONFIRM_RECEIPT_TIME)
                    .setIsDelete(false)
                    .setIsConfirm(false)
                    .setStatus(OrderStatusEnum.PENDING_PAYMENT);
        // @formatter:on
    }

    private void setFreightAmount(Order order, Long memberAddressId) {
        var freightAmount = wareFeignService.getFreight(memberAddressId).getData();
        order.setFreightAmount(freightAmount);
    }

    private void setReceiverInfo(Order order, Long memberAddressId) {
        var address = memberFeignService.getMemberAddressById(memberAddressId).getData();
        // @formatter:off
        order.setReceiverCity(address.getCity())
                .setReceiverDetailAddress(address.getDetailAddress())
                .setReceiverName(address.getName())
                .setReceiverPhone(address.getPhone())
                .setReceiverPostCode(address.getPostalCode())
                .setReceiverProvince(address.getProvince())
                .setReceiverRegion(address.getRegion());
        // @formatter:on
    }

    private List<OrderItem> getOrderItemList(OrderSubmitDTO orderSubmitDTO, String orderSn) {
        // @formatter:off
        return cartFeignService
                        .listSelectedCartItem()
                        .getData()
                        .stream()
                        .map(orderItemService::getOrderItem)//设置基本信息
                        .map(orderItem->orderItem.setOrderSn(orderSn))//设置订单号信息
                        .collect(Collectors.toList());
        // @formatter:on
    }

    private void setOrderPrice(Order order, List<OrderItem> orderItemList) {
        // @formatter:off
        var totalAmount=orderItemList.stream()
                            .map(OrderItem::getPayAmount)
                            .reduce(BigDecimal::add)
                            .orElseThrow(()->new OrderSubmitException("订单金额计算异常"));
        // @formatter:on
        order.setTotalAmount(totalAmount);
        order.setPayAmount();
    }

    private void skuLock(List<OrderItem> orderItems, Order order) {
        // @formatter:off
        var skuLockDTO=new SkuLockDTO()
                                                .setOrderSn(order.getOrderSn())
                                                .setConsignee(order.getReceiverName())
                                                .setPhone(order.getReceiverPhone());
        var orderTaskDTOs=orderItems
                                                .stream()
                                                .map(SkuLockDTO.OrderTaskDTO::convertFor)
                                                .collect(Collectors.toList());
        skuLockDTO.setOrderTaskDTOs(orderTaskDTOs);
        // @formatter:on
        if (!wareFeignService.lock(skuLockDTO).isOk())
            throw new OrderSubmitException("库存锁定失败");
    }

    private void sendOrderCloseDelayMsg(String orderSn) {
        var topicAndTag = MQConstant.getTopicAndTag(MQConstant.Order.TOPIC, MQConstant.Order.TAG_CLOSE);
        rocketTemplate.syncSend(topicAndTag, MessageBuilder.withPayload(orderSn).build(), 10000,
                MQConstant.Order.ORDER_CLOSE_DELAY_TIME);
    }

    private void sendOrderCloseMsg(String orderSn) {
        var topicAndTag = MQConstant.getTopicAndTag(MQConstant.Order.TOPIC, MQConstant.Order.TAG_CLOSE);
        rocketTemplate.convertAndSend(topicAndTag, orderSn);
    }

    private PaymentInfo getPaymentInfo(Order order) {
        // @formatter:off
        return new PaymentInfo()
                                .setOrderId(order.getId())
                                .setOrderSn(order.getOrderSn())
                                .setPayAmount(order.getPayAmount());
        // @formatter:on
    }
}
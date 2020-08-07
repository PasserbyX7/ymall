package com.yinn.ymall.ware.service.impl;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.common.constant.MQConstant;
import com.yinn.ymall.common.dto.SkuHasStockDTO;
import com.yinn.ymall.ware.constant.OrderStatusEnum;
import com.yinn.ymall.ware.constant.OrderTaskStatusEnum;
import com.yinn.ymall.ware.dao.SkuStockDao;
import com.yinn.ymall.ware.dto.SkuLockDTO;
import com.yinn.ymall.ware.dto.SkuStockPageQueryDTO;
import com.yinn.ymall.ware.entity.OrderTask;
import com.yinn.ymall.ware.entity.SkuStock;
import com.yinn.ymall.ware.exception.StockShortageException;
import com.yinn.ymall.ware.feign.OrderFeignService;
import com.yinn.ymall.ware.service.OrderTaskService;
import com.yinn.ymall.ware.service.SkuStockService;

@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockDao, SkuStock> implements SkuStockService {

    @Autowired
    private RocketMQTemplate rocketTemplate;

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private OrderTaskService orderTaskService;

    @Override
    public List<SkuHasStockDTO> listSkuHasStock(List<Long> skuIds) {
        // @formatter:off
        return list(
                    Wrappers.<SkuStock>lambdaQuery()
                                    .in(SkuStock::getSkuId, skuIds)
                    )
                    .stream()
                    .map(e->new SkuHasStockDTO().setSkuId(e.getSkuId()).setIsHasStock(e.getIsHasStock()))
                    .collect(Collectors.toList());
        // @formatter:on
    }

    @Override
    public SkuStock getBySkuId(Long skuId) {
        return getOne(Wrappers.<SkuStock>lambdaQuery().eq(SkuStock::getSkuId, skuId));
    }

    @Transactional
    @Override
    public void stockLock(SkuLockDTO skuLockDTO) {
        // 库存锁定
        for (var item : skuLockDTO.getOrderTaskDTOs()) {
            SkuStock skuStock = getBySkuId(item.getSkuId());// 根据id查出对应实体对象
            if (!skuStock.IsStockLockSuccess(item.getCount()))// 如果库存不足，则抛出异常
                throw new StockShortageException();
            skuStock.setStockLock(skuStock.getStockLock() + item.getCount());
            update(Wrappers.<SkuStock>lambdaUpdate(skuStock));
        }
        // 保存工单
        orderTaskService.saveBatch(skuLockDTO.convertToOrderTaskList());
        //发送库存延时解锁消息
        sendWareUnlockDelayMsg(skuLockDTO.getOrderSn());
    }

    /**
     * 解锁库存
     *
     * @param orderSn 订单号
     * @Date: 2020-05-22 22:05:37
     */
    @Transactional
    @Override
    public void stockUnlock(String orderSn) {
        var order = orderFeignService.getOrderBySn(orderSn).getData();
        //如果订单不存在（订单创建发生异常）或订单已关闭，则必须解锁库存
        if (order == null || order.getStatus() == OrderStatusEnum.CLOSED)
            orderTaskService.listByOrderSn(orderSn).forEach(this::stockUnlock);
    }

    /**
     * 扣减库存
     *
     * @param orderSn 订单号
     * @Date: 2020-07-26 03:54:16
     */
    @Transactional
    @Override
    public void stockDeduct(String orderSn) {
        var order = orderFeignService.getOrderBySn(orderSn).getData();
        if (order != null && order.getStatus() == OrderStatusEnum.PENDING_SHIPPED)
            orderTaskService.listByOrderSn(orderSn).forEach(this::stockDeduct);
    }

    @Transactional
    @Override
    public Boolean getSkuHasStock(Long skuId) {
        return getOne(Wrappers.<SkuStock>lambdaQuery().eq(SkuStock::getSkuId, skuId)).getIsHasStock();
    }

    @Override
    public Page<SkuStock> queryPage(SkuStockPageQueryDTO query) {
        var w = Wrappers.<SkuStock>lambdaQuery().eq(query.getKey() != null, SkuStock::getId, query.getKey());
        return (Page<SkuStock>) page(query.page(), w);
    }

    private void stockUnlock(OrderTask orderTask){
        //根据工单查出对应SkuStock实体
        SkuStock skuStock = getBySkuId(orderTask.getSkuId());
        //解锁库存并保存
        skuStock.setStockLock(skuStock.getStockLock() - orderTask.getCount());
        updateById(skuStock);
        //更改工单状态并保存
        orderTask.setStatus(OrderTaskStatusEnum.UNLOCKED);
        orderTaskService.save(orderTask);
    }

    private void stockDeduct(OrderTask orderTask){
        //根据工单查出对应SkuStock实体
        SkuStock skuStock = getBySkuId(orderTask.getSkuId());
        //扣减库存并保存
        skuStock.setStockLock(skuStock.getStockLock() - orderTask.getCount());
        skuStock.setStock(skuStock.getStock()-orderTask.getCount());
        updateById(skuStock);
        //更改工单状态并保存
        orderTask.setStatus(OrderTaskStatusEnum.BUYING);
        orderTaskService.save(orderTask);
    }

    private void sendWareUnlockDelayMsg(String orderSn) {
        var topicAndTag=MQConstant.getTopicAndTag(MQConstant.Ware.TOPIC, MQConstant.Ware.TAG_UNLOCK);
        rocketTemplate.syncSend(topicAndTag, MessageBuilder.withPayload(orderSn).build(),10000, MQConstant.Ware.WARE_UNLOCK_DELAY_TIME);
    }

}
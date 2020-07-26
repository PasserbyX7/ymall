package com.yinn.ymall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.order.constant.OrderStatusEnum;
import com.yinn.ymall.order.dto.OrderConfirmDTO;
import com.yinn.ymall.order.dto.OrderPaidDTO;
import com.yinn.ymall.order.dto.OrderSubmitDTO;
import com.yinn.ymall.order.dto.PrePaymentDTO;
import com.yinn.ymall.order.entity.Order;

/**
 * 订单表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
public interface OrderService extends IService<Order> {

    OrderConfirmDTO getOrderConfirmDTO(Long memberId);
    void submit(OrderSubmitDTO orderSubmitDTO);
	Order getByOrderSn(String orderSn);
	Order getByOrderStatus(Long userId,OrderStatusEnum orderStatus);
    void closeOrder(String orderSn);
    void orderPaid(OrderPaidDTO orderPaidDTO);
    PrePaymentDTO wxMiniPay(String orderSn,String openId);
}


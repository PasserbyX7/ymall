package com.yinn.ymall.order.listener;

import com.yinn.ymall.common.constant.MQConstant;
import com.yinn.ymall.order.service.OrderService;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = MQConstant.Order.TOPIC, consumerGroup = "order-"+MQConstant.Order.ORDER_CLOSE_CONSUMER_GROUP, selectorExpression = MQConstant.Order.TAG_CLOSE)
public class OrderCloseListener implements RocketMQListener<String>{

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(String orderSn) {
        orderService.closeOrder(orderSn);
    }

}
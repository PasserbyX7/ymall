package com.yinn.ymall.ware.listener;

import com.yinn.ymall.common.constant.MQConstant;
import com.yinn.ymall.ware.dto.OrderPaidDTO;
import com.yinn.ymall.ware.service.SkuStockService;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = MQConstant.Order.TOPIC, consumerGroup = MQConstant.Order.ORDER_CLOSE_CONSUMER_GROUP, selectorExpression = MQConstant.Order.TAG_PAID)
public class WareDeductListener implements RocketMQListener<OrderPaidDTO>{

    @Autowired
    private SkuStockService skuStockService;

    @Override
    public void onMessage(OrderPaidDTO orderPaidDTO){
        skuStockService.stockDeduct(orderPaidDTO.getOrderSn());
    }

}
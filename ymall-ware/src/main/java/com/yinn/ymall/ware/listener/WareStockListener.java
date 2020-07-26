package com.yinn.ymall.ware.listener;

import com.yinn.ymall.common.constant.MQConstant;
import com.yinn.ymall.ware.service.SkuStockService;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = MQConstant.Ware.TOPIC, consumerGroup = MQConstant.Ware.WARE_UNLOCK_CONSUMER_GROUP, selectorExpression = MQConstant.Ware.TAG_UNLOCK)
public class WareStockListener implements RocketMQListener<String>{

    @Autowired
    private SkuStockService skuStockService;

    @Override
    public void onMessage(String orderSn) {
        skuStockService.stockUnlock(orderSn);
    }

}
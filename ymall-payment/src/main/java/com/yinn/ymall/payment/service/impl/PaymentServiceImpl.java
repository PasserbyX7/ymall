package com.yinn.ymall.payment.service.impl;

import java.math.BigDecimal;

import com.lly835.bestpay.service.BestPayService;
import com.yinn.ymall.common.constant.MQConstant;
import com.yinn.ymall.payment.constant.WxConstant;
import com.yinn.ymall.payment.dto.OrderPaidDTO;
import com.yinn.ymall.payment.dto.PaymentInfoDTO;
import com.yinn.ymall.payment.dto.PrePaymentDTO;
import com.yinn.ymall.payment.service.PaymentService;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private RocketMQTemplate rocketTemplate;

    @Override
    public PrePaymentDTO createPayment(PaymentInfoDTO paymentInfoDTO) {
        var payRequest=paymentInfoDTO.convertToPayRequest();
        var payResponse=bestPayService.pay(payRequest);
        return PrePaymentDTO.convertFor(payResponse);
    }

    @Override
    public String asyncNotify(String notifyData) {
        var payResponse=bestPayService.asyncNotify(notifyData);//bestPayService默认实现了签名校验
        // @formatter:off
        var orderPaidDTO=new OrderPaidDTO()
                                            .setOrderSn(payResponse.getOrderId())
                                            .setPayAmount(BigDecimal.valueOf(payResponse.getOrderAmount()))
                                            .setTradeNo(payResponse.getOutTradeNo());
        // @formatter:on
        sendOrderPaidMsg(orderPaidDTO);
        return WxConstant.asyncNotifyReturnMsg;
    }

    private void sendOrderPaidMsg(OrderPaidDTO orderPaidDTO) {
        var topicAndTag=MQConstant.getTopicAndTag(MQConstant.Order.TOPIC, MQConstant.Order.TAG_PAID);
        rocketTemplate.convertAndSend(topicAndTag,orderPaidDTO);
    }
}
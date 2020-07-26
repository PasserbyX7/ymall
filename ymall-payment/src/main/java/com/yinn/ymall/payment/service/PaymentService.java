package com.yinn.ymall.payment.service;

import com.yinn.ymall.payment.dto.PaymentInfoDTO;
import com.yinn.ymall.payment.dto.PrePaymentDTO;

public interface PaymentService {

    PrePaymentDTO createPayment(PaymentInfoDTO paymentInfoDTO);

    /**
     * 为能处理重复通知，该方法必须满足幂等性
     *
     * @param notifyData 微信返回xml文档
     * @Date: 2020-06-11 23:05:11
     */
    String asyncNotify(String notifyData);
}
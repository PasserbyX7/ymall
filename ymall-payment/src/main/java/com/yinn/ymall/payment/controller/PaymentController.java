package com.yinn.ymall.payment.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.payment.dto.PaymentInfoDTO;
import com.yinn.ymall.payment.dto.PrePaymentDTO;
import com.yinn.ymall.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * PaymentController
 */
@Api(tags = "支付接口")
@RequestMapping("/api/payment/v1/payments")
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @ApiOperation("微信小程序支付")
    @PostMapping("/wx-mini")
    public R<PrePaymentDTO>wxMiniPay(@RequestBody PaymentInfoDTO paymentInfoDTO){
        return R.ok(paymentService.createPayment(paymentInfoDTO));
    }

    @ApiOperation("微信支付回调")
    @PostMapping("/async-notify/wx-mini")
    public String asyncNotify(@RequestBody String notifyData){
        return paymentService.asyncNotify(notifyData);
    }

}
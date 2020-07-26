package com.yinn.ymall.third.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.third.service.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "阿里云SMS服务接口")
@RestController
@RequestMapping("/api/third/v1/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @ApiOperation("短信发送")
    @GetMapping("/actions/send")
    public R<Void> sendCode(String phone,String code) {
        smsService.sendCode(phone, code);
        return R.ok();
    }
}
package com.yinn.ymall.uaa.controller;

import com.yinn.ymall.uaa.dto.UserRegisterDTO;
import com.yinn.ymall.uaa.feign.MemberFeignService;
import com.yinn.ymall.uaa.service.SmsService;
import com.yinn.ymall.uaa.service.WxMiniService;
import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户注册接口")
@RestController
@RequestMapping("/api/uaa/v1/register")
public class RegisterController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private WxMiniService wxMiniService;

    @Autowired
    private MemberFeignService memberFeignService;

    @ApiOperation("用户注册")
    @PostMapping
    public R<Void>register(@RequestBody UserRegisterDTO user){
        if(!smsService.verifyAndRemoveCode(user.getPhone(), user.getSmsCode()))//短信验证码校验
            return R.fail(ErrorCode.USER_SMS_CODE_VALIDATE_ERROR);
        user.setOpenId(wxMiniService.getOpenId(user.getWxMiniCode()));
        return memberFeignService.register(user);
    }

    @ApiOperation("发送短信验证码")
    @PostMapping("/actions/send-code")
    public R<Void>register(String phone){
        smsService.sendAndSaveCode(phone);
        return R.ok();
    }

}
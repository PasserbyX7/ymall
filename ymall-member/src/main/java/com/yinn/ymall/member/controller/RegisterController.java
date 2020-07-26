package com.yinn.ymall.member.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.member.dto.UserRegisterDTO;
import com.yinn.ymall.member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户注册接口", description = "为uaa服务所调用")
@RestController
@RequestMapping("/api/member/v1/register")
public class RegisterController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("用户注册")
    @PostMapping
    public R<Void> register(@RequestBody UserRegisterDTO user){
        memberService.register(user);
        return R.ok();
    }

}
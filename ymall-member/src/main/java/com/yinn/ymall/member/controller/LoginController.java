package com.yinn.ymall.member.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.member.dto.UserDetailsDTO;
import com.yinn.ymall.member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "用户登录接口",description = "为uaa服务所调用")
@RestController
@RequestMapping("/api/member/v1/login")
public class LoginController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public R<UserDetailsDTO>getUserDetails(@RequestParam String username){
        return R.ok(memberService.getUserDetails(username));
    }

}
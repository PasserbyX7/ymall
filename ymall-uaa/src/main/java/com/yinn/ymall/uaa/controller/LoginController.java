package com.yinn.ymall.uaa.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.uaa.dto.UsernameLoginDTO;
import com.yinn.ymall.uaa.dto.WxMiniLoginDTO;
import com.yinn.ymall.uaa.service.UsernameLoginService;
import com.yinn.ymall.uaa.service.WxMiniLoginService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/api/uaa/v1")
public class LoginController {

    @Autowired
    private UsernameLoginService usernameLoginService;

    @Autowired
    private WxMiniLoginService wxMiniLoginService;

    @ApiOperation("账号名密码登录")
    @PostMapping("/login")
    public R<String>login(@RequestBody UsernameLoginDTO user){
        return R.ok(usernameLoginService.login(user));
    }

    @ApiOperation("微信小程序登录")
    @PostMapping("/wx-mini/login")
    public R<String>wxMiniLogin(@RequestBody WxMiniLoginDTO user){
        return R.ok(wxMiniLoginService.login(user));
    }

}
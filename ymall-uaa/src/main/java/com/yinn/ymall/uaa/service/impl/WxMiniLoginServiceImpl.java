package com.yinn.ymall.uaa.service.impl;

import com.yinn.ymall.uaa.dto.WxMiniLoginDTO;
import com.yinn.ymall.uaa.service.OAuthService;
import com.yinn.ymall.uaa.service.WxMiniLoginService;
import com.yinn.ymall.uaa.service.WxMiniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxMiniLoginServiceImpl implements WxMiniLoginService{

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private WxMiniService wxMiniService;

    @Override
    public String login(WxMiniLoginDTO user) {
        var openId=wxMiniService.getOpenId(user.getCode());
        return oAuthService.getToken(openId,user.getPassword());
    }


}
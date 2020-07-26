package com.yinn.ymall.uaa.service.impl;

import java.util.Optional;

import com.alibaba.fastjson.JSON;
import com.yinn.ymall.common.config.property.WxProperties;
import com.yinn.ymall.uaa.exception.UserOAuthLoginException;
import com.yinn.ymall.uaa.service.WxMiniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@EnableConfigurationProperties(WxProperties.class)
public class WxMiniServiceImpl implements WxMiniService{

    @Autowired
    private WxProperties wxProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getOpenId(String code) {
        var json=wxMiniLogin(code);
        return parseOpenId(json);
    }

    private String wxMiniLogin(String code){
        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.set("appid",wxProperties.getAppId());
        map.set("secret",wxProperties.getAppSecret());
        map.set("grant_type","authorization_code");
        map.set("js_code",code);
        return restTemplate.postForObject(WxProperties.WX_MINI_OAUTH_API,map,String.class);
    }

    private String parseOpenId(String json){
        // @formatter:off
        return Optional
                        .ofNullable(json)
                        .map(JSON::parseObject)
                        .map(jsonObject->jsonObject.get("openid"))
                        .map(String::valueOf)
                        .orElseThrow(UserOAuthLoginException::new);
        // @formatter:on
    }
}
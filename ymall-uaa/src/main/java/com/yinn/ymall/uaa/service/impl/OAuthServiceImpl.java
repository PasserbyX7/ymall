package com.yinn.ymall.uaa.service.impl;

import com.yinn.ymall.uaa.service.OAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthServiceImpl implements OAuthService{

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getToken(String username, String password) {
        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.set("username",username);
        map.set("password",password);
        map.set("grant_type","password");
        var url=String.format("http://localhost:%d/oauth/token", port);
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("user","secret"));
        var token=restTemplate.postForObject(url,map,OAuth2AccessToken.class);
        return "bearer "+token.getValue();
    }

}
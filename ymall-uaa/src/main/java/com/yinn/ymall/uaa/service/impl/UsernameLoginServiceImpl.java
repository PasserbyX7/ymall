package com.yinn.ymall.uaa.service.impl;

import com.yinn.ymall.uaa.dto.UsernameLoginDTO;
import com.yinn.ymall.uaa.service.OAuthService;
import com.yinn.ymall.uaa.service.UsernameLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsernameLoginServiceImpl implements UsernameLoginService {

    @Autowired
    private OAuthService oAuthService;

    @Override
    public String login(UsernameLoginDTO user) {
        return oAuthService.getToken(user.getUsername(), user.getPassword());
    }

}
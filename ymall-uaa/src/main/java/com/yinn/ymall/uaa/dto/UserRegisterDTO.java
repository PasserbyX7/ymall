package com.yinn.ymall.uaa.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String phone;
    private String smsCode;
    private String wxMiniCode;
    private String openId;
}
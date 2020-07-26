package com.yinn.ymall.third.service;

public interface SmsService {
    void sendCode(String phone,String code);
}
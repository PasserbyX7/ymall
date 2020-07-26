package com.yinn.ymall.uaa.service;

public interface SmsService {
    void sendAndSaveCode(String phone);
    boolean verifyAndRemoveCode(String phone,String code);
}
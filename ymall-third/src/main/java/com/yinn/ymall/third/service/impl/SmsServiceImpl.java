package com.yinn.ymall.third.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yinn.ymall.common.exception.SmsSendException;
import com.yinn.ymall.third.config.property.SmsProperties;
import com.yinn.ymall.third.service.SmsService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(SmsProperties.class)
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void sendCode(String phone, String code) {
        var profile = DefaultProfile.getProfile(smsProperties.getRegionId(), smsProperties.getAccessKeyId(), smsProperties.getAccessSecret());
        var client = new DefaultAcsClient(profile);
        var request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsProperties.getDomain());
        request.setSysVersion(smsProperties.getVersion());
        request.setSysAction(smsProperties.getAction());
        //新版api
        // request.putQueryParameter("To", phone);// 手机号，格式：国际码+号码
        // request.putQueryParameter("From", sign);// 签名
        request.putQueryParameter("PhoneNumbers", phone);// 手机号，格式：国际码+号码
        request.putQueryParameter("SignName", smsProperties.getSign());// 签名
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());// 模板
        request.putQueryParameter("TemplateParam", getTemplateParam(code));// 验证码
        try {
            var response = client.getCommonResponse(request);
            if (!StringUtils.equals("OK", getResponseCode(response.getData())))
                throw new SmsSendException();
        } catch (Exception e) {
            throw new SmsSendException();
        }
    }

    private static String getTemplateParam(String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        return JSON.toJSONString(map);
    }

    private static String getResponseCode(String json) {
        // return (String) JSON.parseObject(json, Map.class).get("ResponseCode");
        return (String) JSON.parseObject(json, Map.class).get("Message");
    }

}
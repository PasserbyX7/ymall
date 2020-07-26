package com.yinn.ymall.uaa.service.impl;

import java.util.concurrent.TimeUnit;

import com.yinn.ymall.common.exception.SmsSendException;
import com.yinn.ymall.uaa.constant.RedisConstant;
import com.yinn.ymall.uaa.feign.ThirdFeignService;
import com.yinn.ymall.uaa.service.SmsService;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * SmsServiceImpl
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private ThirdFeignService thirdFeignService;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public void sendAndSaveCode(String phone) {
        String code = sendCode(phone);
        saveCode(phone, code);
    }

    @Override
    public boolean verifyAndRemoveCode(String phone, String code) {
        var isMatch=StringUtils.equals(code, getCode(phone));
        removeCode(phone);
        return isMatch;
    }

    private void removeCode(String phone) {
        String key = RedisConstant.SMS_CODE_PRE + phone;
        template.delete(key);
    }

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return 验证码
     * @Date: 2020-07-20 06:22:23
     */
    private String getCode(String phone) {
        String key = RedisConstant.SMS_CODE_PRE + phone;
        return template.opsForValue().get(key);
    }

    /**
     * 保存验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @Date: 2020-05-13 00:51:52
     */
    private void saveCode(String phone, String code) {
        String key = RedisConstant.SMS_CODE_PRE + phone;
        long timeout = RedisConstant.SMS_CODE_EXPIRE_TIME;
        template.opsForValue().set(key, code, timeout, TimeUnit.MINUTES);
    }

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return 验证码
     * @Date: 2020-07-20 06:22:23
     */
    private String sendCode(String phone) {
        String code = RandomStringUtils.randomNumeric(6);
        if (!thirdFeignService.sendCode(phone, code).isOk())// 如果短信发送失败
            throw new SmsSendException();
        return code;
    }
}
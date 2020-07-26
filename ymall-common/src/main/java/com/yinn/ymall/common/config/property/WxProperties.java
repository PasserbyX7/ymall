package com.yinn.ymall.common.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("wx")
public class WxProperties {
    public static final String WX_MINI_OAUTH_API="https://api.weixin.qq.com/sns/jscode2session";
    private String appId;
    private String appSecret;
    private String mchId;
    private String mchKey;
    private String notifyUrl;
    private String keyPath;
}
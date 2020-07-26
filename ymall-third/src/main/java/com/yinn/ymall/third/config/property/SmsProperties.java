package com.yinn.ymall.third.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
public class SmsProperties {
    private String domain;
    private String version;
    private String action;
    private String regionId;
    private String sign;
    private String templateCode;
    private String accessKeyId;
    private String accessSecret;
}
package com.yinn.ymall.payment.config;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.yinn.ymall.common.config.property.WxProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WxProperties.class)
public class PaymentConfiguration {

    @Autowired
    private WxProperties wxProperties;

    @Bean
    public BestPayService bestPayService(){
        var wxPayConfig=new WxPayConfig();
        wxPayConfig.setMiniAppId(wxProperties.getAppId());
        wxPayConfig.setMchId(wxProperties.getMchId());
        wxPayConfig.setMchKey(wxProperties.getMchKey());
        wxPayConfig.setNotifyUrl(wxProperties.getNotifyUrl());
        wxPayConfig.setKeyPath(wxProperties.getKeyPath());
        var bestPayService=new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        return bestPayService;
    }

}
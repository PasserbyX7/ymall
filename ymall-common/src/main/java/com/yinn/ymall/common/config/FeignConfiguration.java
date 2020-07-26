package com.yinn.ymall.common.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;

@Configuration
@EnableFeignClients("**.feign")
public class FeignConfiguration {
    /**
     * 在Feign代理时，为header附上JWT令牌
     *
     * @Date: 2020-05-20 14:36:33
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            var jwt = attributes.getRequest().getHeader("Authorization");
            if (StringUtils.isNotBlank(jwt))
                template.header("Authorization", jwt);
        };
    }
}
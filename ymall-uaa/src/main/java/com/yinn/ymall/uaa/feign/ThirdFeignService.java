package com.yinn.ymall.uaa.feign;

import com.yinn.ymall.common.api.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("third-service")
public interface ThirdFeignService {

    @GetMapping("/api/third/v1/sms/actions/send")
    public R<Void> sendCode(@RequestParam String phone,@RequestParam String code);

}
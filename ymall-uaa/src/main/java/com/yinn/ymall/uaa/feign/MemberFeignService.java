package com.yinn.ymall.uaa.feign;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.uaa.dto.UserDetailsDTO;
import com.yinn.ymall.uaa.dto.UserRegisterDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("member-service")
public interface MemberFeignService {

    @PostMapping("/api/member/v1/register")
    R<Void> register(@RequestBody UserRegisterDTO user);

    @PostMapping("/api/member/v1/login")
    R<UserDetailsDTO>getUserDetails(@RequestParam String username);

}
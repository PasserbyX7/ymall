package com.yinn.ymall.third.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.third.dto.SignatureDTO;
import com.yinn.ymall.third.service.OssService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "阿里云OSS服务接口")
@RestController
@RequestMapping("/api/third/v1/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("文件上传服务端签名获取")
    @GetMapping("/actions/policy")
    public R<SignatureDTO> policy() {
        return R.ok(ossService.policy());
    }

}
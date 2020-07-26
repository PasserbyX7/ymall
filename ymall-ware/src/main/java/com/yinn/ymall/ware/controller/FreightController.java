package com.yinn.ymall.ware.controller;

import java.math.BigDecimal;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.ware.service.FreightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "运费接口")
@RestController
@RequestMapping("/api/ware/v1/freight")
public class FreightController {

    @Autowired
    private FreightService freightService;

    @ApiOperation("计算运费")
    @GetMapping("/member-addresses/{memberAddressId}")
    public R<BigDecimal> getFreight(@PathVariable Long memberAddressId){
        return R.ok(freightService.getFreight(memberAddressId));
    }

}
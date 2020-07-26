package com.yinn.ymall.member.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.member.entity.MemberAddress;
import com.yinn.ymall.member.service.MemberAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "会员地址接口")
@RestController
@RequestMapping("/api/member/v1/member-addresses")
public class MemberAddressController {

    @Autowired
    private MemberAddressService memberAddressService;

    @ApiOperation("通过会员id列出地址")
    @GetMapping("/members/{memberId}")
    public R<List<MemberAddress>> listByMemberId(@PathVariable Long memberId){
        return R.ok(memberAddressService.listByMemberId(memberId));
    }

    @ApiOperation("地址查询")
    @GetMapping("/{memberAddressId}")
    public R<MemberAddress> getById(@PathVariable Long memberAddressId){
        return R.ok(memberAddressService.getById(memberAddressId));
    }

    @ApiOperation("地址新增")
    @PostMapping
    public R<Void> save(MemberAddress memberAddress){
        memberAddressService.save(memberAddress);
        return R.ok();
    }

}
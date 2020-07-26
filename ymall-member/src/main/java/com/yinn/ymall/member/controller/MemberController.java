package com.yinn.ymall.member.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.utils.JwtUtils;
import com.yinn.ymall.member.entity.Member;
import com.yinn.ymall.member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "会员接口")
@RestController
@RequestMapping("/api/member/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("会员查询")
    @GetMapping("/{memberId}")
    public R<Member> getById(@PathVariable Long memberId){
        return R.ok(memberService.getById(memberId));
    }

    @ApiOperation("会员查询（JWT）")
    @GetMapping
    public R<Member> getByJWT(@AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long memberId){
        return R.ok(memberService.getById(memberId));
    }

}
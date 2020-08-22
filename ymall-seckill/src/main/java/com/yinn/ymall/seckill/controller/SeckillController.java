package com.yinn.ymall.seckill.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.seckill.dto.KillDTO;
import com.yinn.ymall.seckill.dto.SeckillSkuRelationDTO;
import com.yinn.ymall.seckill.service.SeckillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "秒杀接口")
@RestController
@RequestMapping("/api/seckill/v1/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    // TODO
    @ApiOperation("获得当前时段参与秒杀的商品")
    @GetMapping("/time")
    public R<List<SeckillSkuRelationDTO>> list() {
        return null;
    }

    @ApiOperation("获得当前时段参与秒杀的商品")
    @PostMapping("/actions/kill")
    public R<String> kill(@RequestBody KillDTO killDTO) {
        // @AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long id
        return R.ok(seckillService.kill(killDTO));
    }

}
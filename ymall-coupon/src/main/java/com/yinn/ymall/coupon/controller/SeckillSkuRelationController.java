package com.yinn.ymall.coupon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.coupon.dto.SeckillSkuRelationPageQueryDTO;
import com.yinn.ymall.coupon.entity.SeckillSkuRelation;
import com.yinn.ymall.coupon.service.SeckillSkuRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * seckill-session与sku的关联表 前端控制器
 * </p>
 *
 * @author Passerby
 * @since 2020-08-14
 */
@RestController
@Api(tags = "秒杀商品关联接口")
@RequestMapping("/api/coupon/v1/seckill-sku-relations")
public class SeckillSkuRelationController {

    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;

    @ApiOperation("秒杀商品关联分页查询")
    @GetMapping
    public R<Page<SeckillSkuRelation>> queryPage(SeckillSkuRelationPageQueryDTO seckillSkuRelationPageQueryDTO) {
        return R.ok(seckillSkuRelationService.queryPage(seckillSkuRelationPageQueryDTO));
    }

}
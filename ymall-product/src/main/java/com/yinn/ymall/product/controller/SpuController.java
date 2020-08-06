package com.yinn.ymall.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.dto.SpuDTO;
import com.yinn.ymall.product.dto.SpuPageQueryDTO;
import com.yinn.ymall.product.entity.Spu;
import com.yinn.ymall.product.service.SpuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "商品spu接口")
@RestController
@RequestMapping("/api/product/v1/spus")
public class SpuController {

    @Autowired
    private SpuService spuService;

    @ApiOperation("spu条件分页查询")
    @GetMapping
    public R<Page<Spu>> queryPage(SpuPageQueryDTO spuQueryDTO) {
        return R.ok(spuService.queryPage(spuQueryDTO));
    }

    @ApiOperation("根据skuId查询spu")
    @GetMapping("/skus/{skuId}")
    public R<Spu> getBySkuId(@PathVariable Long skuId) {
        return R.ok(spuService.getBySkuId(skuId));
    }

    @ApiOperation("spu新增")
    @PostMapping
    public R<Void> save(@RequestBody SpuDTO spuDTO) {
        spuService.save(spuDTO);
        return R.ok();
    }

    @ApiOperation("spu上架")
    @PutMapping("/{spuId}/actions/up")
    public R<Void> up(@PathVariable Long spuId) {
        spuService.up(spuId);
        return R.ok();
    }

    @ApiOperation("spu下架")
    @DeleteMapping("/{spuId}/actions/down")
    public R<Void> down(@PathVariable Long spuId) {
        spuService.down(spuId);
        return R.ok();
    }

}
package com.yinn.ymall.ware.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.dto.SkuHasStockDTO;
import com.yinn.ymall.ware.dto.SkuLockDTO;
import com.yinn.ymall.ware.entity.SkuStock;
import com.yinn.ymall.ware.service.SkuStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "sku库存接口")
@RestController
@RequestMapping("/api/ware/v1/sku-stocks")
public class SkuStockController {

    @Autowired
    private SkuStockService skuStockService;

    @ApiOperation(  "列出skus是否具有库存")
    @GetMapping("/skus/has-stock")
    public R<List<SkuHasStockDTO>> listSkuHasStock(@RequestParam List<Long> skuIds) {
        return R.ok(skuStockService.listSkuHasStock(skuIds));
    }

    @ApiOperation("列出sku是否具有库存")
    @GetMapping("/skus/{skuId}/has-stock")
    public R<Boolean> getSkuHasStock(@PathVariable Long skuId) {
        return R.ok(skuStockService.getSkuHasStock(skuId));
    }

    @ApiOperation("新增库存")
    @PostMapping
    public R<Void> save(@RequestBody SkuStock skuStock) {
        skuStockService.save(skuStock);
        return R.ok();
    }

    @ApiOperation("修改库存")
    @PutMapping
    public R<Void> update(@RequestBody SkuStock skuStock) {
        skuStockService.updateById(skuStock);
        return R.ok();
    }

    @ApiOperation("库存锁定")
    @PostMapping("/actions/lock")
    public R<Void> lock(@RequestBody SkuLockDTO skuLockDTO) {
        skuStockService.stockLock(skuLockDTO);
        return R.ok();
    }

}
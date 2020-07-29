package com.yinn.ymall.product.controller;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.dto.ItemDTO;
import com.yinn.ymall.product.dto.SkuPageQueryDTO;
import com.yinn.ymall.product.entity.Sku;
import com.yinn.ymall.product.service.SkuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "商品sku接口")
@RestController
@RequestMapping("/api/product/v1/skus")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @ApiOperation("sku条件分页查询")
    @GetMapping
    public R<Page<Sku>> queryPage(SkuPageQueryDTO skuQueryDTO){
            return R.ok(skuService.queryPage(skuQueryDTO));
    }

    @ApiOperation("返回skuId对应ItemDTO")
    @GetMapping("/{skuId}/item")
    public R<ItemDTO> getItemDTO(@PathVariable Long skuId) {
        return R.ok(skuService.getItem(skuId));
    }

    @ApiOperation("sku查询")
    @GetMapping("/{skuId}")
    public R<Sku> get(@PathVariable Long skuId) {
        return R.ok(skuService.getById(skuId));
    }

    @ApiOperation("sku价格查询")
    @GetMapping("/{skuId}/price")
    public R<BigDecimal>getPriceById(@PathVariable Long skuId) {
        return R.ok(skuService.getPriceById(skuId));
    }

}
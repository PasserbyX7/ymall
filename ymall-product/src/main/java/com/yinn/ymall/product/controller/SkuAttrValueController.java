package com.yinn.ymall.product.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.service.SkuAttrValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "商品sku属性值接口")
@RestController
@RequestMapping("/api/product/v1/sku-attr-values")
public class SkuAttrValueController {

    @Autowired
    private SkuAttrValueService skuAttrValueService;

    @ApiOperation("列出sku下所有sku属性并json形式返回")
    @GetMapping("/skus/{skuId}/json")
    public R<String> listBySkuIdAsStringList(@PathVariable Long skuId) {
        return R.ok(skuAttrValueService.listBySkuIdAsJson(skuId));
    }

}
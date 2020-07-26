package com.yinn.ymall.product.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.entity.SpuAttrValue;
import com.yinn.ymall.product.service.SpuAttrValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "商品spu属性值接口")
@RestController
@RequestMapping("/api/product/v1/spu-attr-values")
public class SpuAttrValueController {

    @Autowired
    private SpuAttrValueService spuAttrValueService;

    @ApiOperation("列出spu下所有spu属性")
    @GetMapping("/spus/{spuId}")
    public R<List<SpuAttrValue>> listBySpuId(@PathVariable Long spuId) {
        return R.ok(spuAttrValueService.listBySpuId(spuId));
    }

    @ApiOperation("批量更新spu下spu属性")
    @PutMapping("/spus/{spuId}/batch")
    public R<Void> update(@PathVariable Long spuId, @RequestBody List<SpuAttrValue> spuAttrValues) {
        spuAttrValueService.updateBatchBySpuId(spuId, spuAttrValues);
        return R.ok();
    }

}
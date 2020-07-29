package com.yinn.ymall.product.controller;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.dto.PageDTO;
import com.yinn.ymall.common.validator.group.InsertGroup;
import com.yinn.ymall.common.validator.group.UpdateGroup;
import com.yinn.ymall.product.entity.Brand;
import com.yinn.ymall.product.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;

@Api(tags = "商品品牌接口")
@RestController
@RequestMapping("/api/product/v1/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiImplicitParam(name = "key", value = "查询关键字：匹配id或首字母或品牌名（模糊匹配）")
    @ApiOperation("品牌条件分页查询")
    @GetMapping
    public R<Page<Brand>> queryPage(PageDTO<Brand> page, String key) {
        return R.ok(brandService.queryPage(page, key));
    }

    @ApiOperation("列出分类下所有品牌")
    @GetMapping("/categories/{categoryId}")
    public R<List<Brand>> listByBrandId(@PathVariable Long categoryId) {
        return R.ok(brandService.listByCategoryId(categoryId));
    }

    @ApiOperation("品牌查询")
    @GetMapping("/{brandId}")
    public R<Brand> getById(@PathVariable Long brandId) {
        return R.ok(brandService.getById(brandId));
    }

    @ApiOperation("品牌新增")
    @PostMapping
    public R<Void> save(@Validated(InsertGroup.class) @RequestBody Brand brand) {
        brandService.save(brand);
        return R.ok();
    }

    @ApiOperation("品牌修改")
    @PutMapping
    public R<Void> update(@Validated(UpdateGroup.class) @RequestBody Brand brand) {
        brandService.updateById(brand);
        return R.ok();
    }

    @ApiOperation("品牌删除")
    @DeleteMapping("/{brandId}")
    public R<Void> remove(@PathVariable Long brandId) {
        brandService.removeById(brandId);
        return R.ok();
    }


}
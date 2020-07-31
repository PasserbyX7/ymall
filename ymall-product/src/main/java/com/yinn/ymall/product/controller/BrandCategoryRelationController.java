package com.yinn.ymall.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.dto.BrandCategoryRelationDTO;
import com.yinn.ymall.product.entity.BrandCategoryRelation;
import com.yinn.ymall.product.service.BrandCategoryRelationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Api(tags = "商品品牌-分类关联接口")
@RestController
@RequestMapping("/api/product/v1/brand-category-relations")
public class BrandCategoryRelationController {

    @Autowired
    private BrandCategoryRelationService brandCategoryRelationService;

    @ApiOperation("获取品牌-分类关联（附带品牌名和分类名）")
    @GetMapping("/brands/{brandId}")
    public R<List<BrandCategoryRelationDTO>> listByBrandId(@PathVariable Long brandId){
        return R.ok(brandCategoryRelationService.listByBrandId(brandId));
    }

    @ApiOperation("新增品牌-分类关联")
    @PostMapping
    public R<Void>save(@RequestBody BrandCategoryRelation brandCategoryRelation){
        brandCategoryRelationService.save(brandCategoryRelation);
        return R.ok();
    }

    @ApiOperation("删除品牌-分类关联")
    @DeleteMapping("/{id}")
    public R<Void>remove(@PathVariable Long id){
        brandCategoryRelationService.removeById(id);
        return R.ok();
    }

}
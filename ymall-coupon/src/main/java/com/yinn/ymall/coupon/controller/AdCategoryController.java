package com.yinn.ymall.coupon.controller;

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

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.coupon.entity.AdCategory;
import com.yinn.ymall.coupon.service.AdCategoryService;

/**
 * 广告分类表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @Date: 2020-07-12 07:29:05
 */
@Api(tags = "广告分类接口")
@RestController
@RequestMapping("/api/coupon/v1/ad-categories")
public class AdCategoryController {

    @Autowired
    private AdCategoryService adCategoryService;

    @ApiOperation("广告分类查询")
    @GetMapping("/{adCategoryId}")
    public R<AdCategory> getById(@PathVariable Long adCategoryId) {
        return R.ok(adCategoryService.getById(adCategoryId));
    }

    @ApiOperation("广告分类列表")
    @GetMapping
    public R<List<AdCategory>> list() {
        return R.ok(adCategoryService.list());
    }

    @ApiOperation("广告分类新增")
    @PostMapping
    public R<Void> save(@RequestBody AdCategory adCategory) {
        adCategoryService.save(adCategory);
        return R.ok();
    }

    @ApiOperation("广告分类修改")
    @PutMapping
    public R<Void> update(@RequestBody AdCategory adCategory) {
        adCategoryService.updateById(adCategory);
        return R.ok();
    }

    @ApiOperation("广告分类删除")
    @DeleteMapping("/{adCategoryId}")
    public R<Void> remove(@PathVariable Long adCategoryId) {
        adCategoryService.removeById(adCategoryId);
        return R.ok();
    }
}
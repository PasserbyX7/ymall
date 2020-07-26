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
import com.yinn.ymall.coupon.entity.AdImg;
import com.yinn.ymall.coupon.service.AdImgService;

/**
 * 广告表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-07-10 18:21:13
 */
@Api(tags = "广告图接口")
@RestController
@RequestMapping("/api/coupon/v1/ad-imgs")
public class AdImgController {

    @Autowired
    private AdImgService adImgService;

    @ApiOperation("列出分类下所有广告图")
    @GetMapping("/ad-categories/{adCategoryId}")
    public R<List<AdImg>> listByAdCategoryId(@PathVariable Long adCategoryId) {
        return R.ok(adImgService.listByAdCategoryId(adCategoryId));
    }

    @ApiOperation("广告图列表")
    @GetMapping
    public R<List<AdImg>> list() {
        return R.ok(adImgService.list());
    }

    @ApiOperation("广告图查询")
    @GetMapping("/{adImgId}")
    public R<AdImg> getById(@PathVariable Long adImgId) {
        return R.ok(adImgService.getById(adImgId));
    }

    @ApiOperation("广告图新增")
    @PostMapping
    public R<Void> save(@RequestBody AdImg adImg) {
        adImgService.save(adImg);
        return R.ok();
    }

    @ApiOperation("广告图修改")
    @PutMapping
    public R<Void> update(@RequestBody AdImg adImg) {
        adImgService.updateById(adImg);
        return R.ok();
    }

    @ApiOperation("广告图删除")
    @DeleteMapping("/{adImgId}")
    public R<Void> remove(@PathVariable Long adImgId) {
        adImgService.removeById(adImgId);
        return R.ok();
    }
}

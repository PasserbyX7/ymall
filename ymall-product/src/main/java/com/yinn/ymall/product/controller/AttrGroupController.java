package com.yinn.ymall.product.controller;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.dto.AttrGroupDTO;
import com.yinn.ymall.product.dto.AttrGroupQueryDTO;
import com.yinn.ymall.product.entity.AttrGroup;
import com.yinn.ymall.product.service.AttrGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * AttrGroupController
 */
@Api(tags = "商品属性组接口")
@RestController
@RequestMapping("/api/product/v1/attrGroups")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    @ApiOperation("查询属性")
    @GetMapping("/{attrGroupId}")
    public R<AttrGroup> getById(@PathVariable Long attrGroupId) {
        return R.ok(attrGroupService.getById(attrGroupId));
    }

    @ApiOperation("列出分类下所有属性组并附上相应属性")
    @GetMapping("/categories/{categoryId}/with-attrs")
    public R<List<AttrGroupDTO>> listByCategoryIdWithAttrs(@PathVariable Long categoryId) {
        return R.ok(attrGroupService.listByCategoryIdWithAttrs(categoryId));
    }

    @ApiOperation("分页查询某分类下所有属性组")
    @GetMapping
    public R<Page<AttrGroup>> queryPage(AttrGroupQueryDTO attrGroupQueryDTO) {
        return R.ok(attrGroupService.queryPage(attrGroupQueryDTO));
    }

    @ApiOperation("新增属性组")
    @PostMapping
    public R<Void> save(AttrGroup attrGroup) {
        attrGroupService.save(attrGroup);
        return R.ok();
    }

    @ApiOperation("删除属性组")
    @DeleteMapping("/{attrGroupId}")
    public R<Void> remove(Long attrGroupId) {
        attrGroupService.removeById(attrGroupId);
        return R.ok();
    }

}
package com.yinn.ymall.product.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.entity.Category;
import com.yinn.ymall.product.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Api(tags = "商品分类接口")
@RestController
@RequestMapping("/api/product/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("树状查询")
    @GetMapping("/tree")
    public R<List<Category>> listWithTree() {
        return R.ok(categoryService.listWithTree());
    }

    @ApiOperation("分类路径查询")
    @GetMapping("/{categoryId}/path")
    public R<List<Long>> getCategoryPath(@PathVariable Long categoryId) {
        return R.ok(categoryService.getCategoryPath(categoryId));
    }

    @ApiOperation("分类查询")
    @GetMapping("/{categoryId}")
    public R<Category> get(@PathVariable Long categoryId) {
        return R.ok(categoryService.getById(categoryId));
    }

    @ApiOperation("分类新增")
    @PostMapping
    public R<Void> save(@RequestBody Category category) {
        categoryService.save(category);
        return R.ok();
    }

    @ApiOperation("分类修改")
    @PutMapping
    public R<Void> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.ok();
    }

    @ApiOperation("分类批量修改")
    @PutMapping("/batch")
    public R<Void> update(@RequestBody List<Category> categories) {
        categoryService.updateBatchById(categories);
        return R.ok();
    }

    @ApiOperation("分类删除")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id){
        categoryService.removeById(id);
        return R.ok();
    }

    @ApiOperation("分类批量删除")
    @DeleteMapping("/batch")
    public R<Void> delete(@RequestBody List<Long> ids){
        categoryService.removeByIds(ids);
        return R.ok();
    }

}
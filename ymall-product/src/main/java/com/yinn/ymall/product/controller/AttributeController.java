package com.yinn.ymall.product.controller;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.dto.PageDTO;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.entity.Attribute;
import com.yinn.ymall.product.service.AttributeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "商品属性接口")
@RestController
@RequestMapping("/api/product/v1/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @ApiOperation("分页查询某分类下销售属性")
    @GetMapping("/categories/{categoryId}/sku")
    public R<Page<Attribute>> querySkuPageByCategoryId(PageDTO<Attribute>page,String key,@PathVariable Long categoryId){
        return R.ok(attributeService.queryPageByCategoryId(page.page(),key,categoryId,AttrTypeEnum.SKU_TYPE));
    }

    @ApiOperation("分页查询某分类下规格属性")
    @GetMapping("/categories/{categoryId}/spu")
    public R<Page<Attribute>> querySpuPageByCategoryId(PageDTO<Attribute>page,String key,@PathVariable Long categoryId){
        return R.ok(attributeService.queryPageByCategoryId(page.page(),key,categoryId,AttrTypeEnum.SPU_TYPE));
    }

    @ApiOperation("查询某属性组下所有属性")
    @GetMapping("/attrGroups/{attrGroupId}")
    public R<List<Attribute>>listByAttrGroupId(@PathVariable Long attrGroupId){
        return R.ok(attributeService.listByAttrGroupId(attrGroupId));
    }

    @ApiOperation("分页查询可加入某属性组的属性")
    @GetMapping("/attrGroups/{attrGroupId}/invert")
    public R<Page<Attribute>>InvertListByAttrGroupId(PageDTO<Attribute>page,String key,@PathVariable Long attrGroupId){
        return R.ok(attributeService.InvertListByAttrGroupId(page.page(),key,attrGroupId));
    }

    @ApiOperation("新增属性")
    @PostMapping
    public R<Void>save(Attribute attribute){
        attributeService.save(attribute);
        return R.ok();
    }

    @ApiOperation("修改属性")
    @PutMapping
    public R<Void>update(Attribute attribute){
        attributeService.updateById(attribute);
        return R.ok();
    }

    @ApiOperation("删除属性")
    @DeleteMapping("/attributeId")
    public R<Void>remove(@PathVariable Long attributeId){
        attributeService.removeById(attributeId);
        return R.ok();
    }
}
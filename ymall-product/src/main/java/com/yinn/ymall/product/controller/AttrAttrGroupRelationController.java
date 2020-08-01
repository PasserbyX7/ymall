package com.yinn.ymall.product.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.product.entity.AttrAttrGroupRelation;
import com.yinn.ymall.product.service.AttrAttrGroupRelationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "商品属性-属性组接口")
@RestController
@RequestMapping("/api/product/v1/attr-attrGroup-relations")
public class AttrAttrGroupRelationController {

    @Autowired
    private AttrAttrGroupRelationService attrAttrGroupRelationService;

    @ApiOperation("新增属性-属性组关联")
    @PostMapping
    public R<Void>save(@RequestBody AttrAttrGroupRelation attrAttrGroupRelation){
        attrAttrGroupRelationService.save(attrAttrGroupRelation);
        return R.ok();
    }

    @ApiOperation("属性-属性组关联批量增加")
    @PostMapping("/batch")
    public R<Void> save(@RequestBody List<AttrAttrGroupRelation> attrAttrGroupRelations){
        attrAttrGroupRelationService.saveBatch(attrAttrGroupRelations);
        return R.ok();
    }

    @ApiOperation("删除属性-属性组关联")
    @DeleteMapping("/{id}")
    public R<Void>remove(@PathVariable Long id){
        attrAttrGroupRelationService.removeById(id);
        return R.ok();
    }

    @ApiOperation("通过attrId删除属性-属性组关联")
    @DeleteMapping("/attrs/{id}")
    public R<Void>removeByAttrId(@PathVariable Long id){
        attrAttrGroupRelationService.removeByAttrId(id);
        return R.ok();
    }

    @ApiOperation("通过attrId批量删除属性-属性组关联")
    @DeleteMapping("/attrs")
    public R<Void>removeByAttrId(@RequestBody List<Long> ids){
        attrAttrGroupRelationService.removeByAttrIds(ids);
        return R.ok();
    }

    @ApiOperation("属性-属性组关联批量删除")
    @DeleteMapping("/batch")
    public R<Void> delete(@RequestBody List<Long> ids){
        attrAttrGroupRelationService.removeByIds(ids);
        return R.ok();
    }

}
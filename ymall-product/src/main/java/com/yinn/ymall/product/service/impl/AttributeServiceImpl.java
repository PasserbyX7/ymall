package com.yinn.ymall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.constant.SearchTypeEnum;
import com.yinn.ymall.product.dao.AttributeDao;
import com.yinn.ymall.product.entity.AttrAttrGroupRelation;
import com.yinn.ymall.product.entity.Attribute;
import com.yinn.ymall.product.service.AttrAttrGroupRelationService;
import com.yinn.ymall.product.service.AttrGroupService;
import com.yinn.ymall.product.service.AttributeService;

@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeDao, Attribute> implements AttributeService {

    @Autowired
    private AttrAttrGroupRelationService attrAttrGroupRelationService;
    @Autowired
    private AttrGroupService attrGroupService;

    @Override
    public List<Attribute> listByAttrGroupId(Long attrGroupId) {
        return baseMapper.listByAttrGroupId(attrGroupId);
    }

    @Override
    public boolean save(Attribute attribute) {
        // 保存基本数据
        baseMapper.insert(attribute);
        // 维护attr-attrGroup表
        attrAttrGroupRelationService.save(
                new AttrAttrGroupRelation().setAttrGroupId(attribute.getAttrGroupId()).setAttrId(attribute.getId()));
        return true;
    }

    @Override
    public boolean updateById(Attribute attribute) {
        // 修改基本数据
        baseMapper.updateById(attribute);
        // 维护attr-attrGroup表
        attrAttrGroupRelationService.update(Wrappers.<AttrAttrGroupRelation>lambdaUpdate()
                .set(AttrAttrGroupRelation::getAttrGroupId, attribute.getAttrGroupId())
                .eq(AttrAttrGroupRelation::getAttrId, attribute.getId()));
        return true;
    }

    @Override
    public Page<Attribute> queryPageByCategoryId(Page<Attribute> page, String key, Long categoryId, AttrTypeEnum type) {
        var w = Wrappers.<Attribute>lambdaQuery().eq(Attribute::getType, type);
        if (StringUtils.hasText(key))
            w.and(e -> e.eq(Attribute::getId, key).or().like(Attribute::getName, key));
        if (!Objects.equals(categoryId, 0L))
            w.eq(Attribute::getCategoryId, categoryId);
        return page(page, w);
    }

    @Override
    public Page<Attribute> InvertListByAttrGroupId(Page<Attribute> page, String key, Long attrGroupId) {
        // 查出属性组所属分类id
        Long categoryId = attrGroupService.getById(attrGroupId).getCategoryId();
        // 查出分类下所有规格属性
        // @formatter:off
        var attrIds = list(Wrappers.<Attribute>lambdaQuery().eq(Attribute::getCategoryId, categoryId)
                                .eq(Attribute::getType, AttrTypeEnum.SPU_TYPE))
                                .stream()
                                .map(Attribute::getId)
                                .collect(Collectors.toList());
        var wrapper = Wrappers.<Attribute>lambdaQuery()
                                                .eq(Attribute::getCategoryId, categoryId)
                                                .eq(Attribute::getType,AttrTypeEnum.SPU_TYPE);
        // @formatter:on
        if (!CollectionUtils.isEmpty(attrIds))
            wrapper.notIn(Attribute::getId, attrIds);
        if (StringUtils.hasText(key))
            wrapper.and(e -> e.eq(Attribute::getId, key).or().like(Attribute::getName, key));
        return page(page, wrapper);
    }

    @Override
    public List<Attribute> listSearchAttrsByIds(List<Long> attrIds) {
        return list(Wrappers.<Attribute>lambdaQuery().eq(Attribute::getSearchType, SearchTypeEnum.KEYWORD_SEARCH)
                .in(Attribute::getId, attrIds));
    }

}
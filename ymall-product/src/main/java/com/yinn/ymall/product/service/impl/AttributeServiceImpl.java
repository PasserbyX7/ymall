package com.yinn.ymall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.constant.SearchTypeEnum;
import com.yinn.ymall.product.dao.AttributeDao;
import com.yinn.ymall.product.dto.AttrPageQueryDTO;
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
        // @formatter:off
        attrAttrGroupRelationService.save(
                new AttrAttrGroupRelation()
                            .setAttrGroupId(attribute.getAttrGroupId())
                            .setAttrId(attribute.getId())
            );
        // @formatter:on
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
    public Page<Attribute> queryPage(AttrPageQueryDTO query) {
        var w = Wrappers.<Attribute>lambdaQuery();
        w.eq(query.getType() != null, Attribute::getType, query.getType());
        String key = query.getKey();
        if (StringUtils.hasText(key))
            w.and(e -> e.eq(Attribute::getId, key).or().like(Attribute::getName, key));
        // @formatter:off
        //为返回值增加attrGroupId字段值
        var result= page(query.page(), w);
        var records=result.getRecords()
                                    .stream()
                                    .map(e->e.setAttrGroupId(attrAttrGroupRelationService.getAttrGroupIdByAttrId(e.getId())))
                                    .collect(Collectors.toList());
        result.setRecords(records);
        // @formatter:on
        return result;
    }

    @Override
    public List<Attribute> InvertListByAttrGroupId(String key, Long attrGroupId) {
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
        wrapper.notIn(!CollectionUtils.isEmpty(attrIds), Attribute::getId, attrIds);
        wrapper.and(StringUtils.hasText(key), e -> e.eq(Attribute::getId, key).or().like(Attribute::getName, key));
        return list(wrapper);
    }

    @Override
    public List<Attribute> listSearchAttrsByIds(List<Long> attrIds) {
        // @formatter:off
        return list(
            Wrappers.<Attribute>lambdaQuery()
                            .eq(Attribute::getSearchType, SearchTypeEnum.KEYWORD_SEARCH)
                            .in(Attribute::getId, attrIds)
        );
        // @formatter:on
    }

}
package com.yinn.ymall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.constant.SearchTypeEnum;
import com.yinn.ymall.product.dao.AttributeDao;
import com.yinn.ymall.product.dto.AttrPageQueryDTO;
import com.yinn.ymall.product.dto.AttributeDTO;
import com.yinn.ymall.product.entity.AttrAttrGroupRelation;
import com.yinn.ymall.product.entity.Attribute;
import com.yinn.ymall.product.service.AttrAttrGroupRelationService;
import com.yinn.ymall.product.service.AttrGroupService;
import com.yinn.ymall.product.service.AttributeService;

@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeDao, Attribute> implements AttributeService {

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private AttrAttrGroupRelationService attrAttrGroupRelationService;

    @Override
    public List<Attribute> listByAttrGroupId(Long attrGroupId) {
        return baseMapper.listByAttrGroupId(attrGroupId);
    }

    @Override
    public Page<AttributeDTO> queryPage(AttrPageQueryDTO query) {
        var w = Wrappers.<Attribute>lambdaQuery();
        w.eq(query.getType() != null, Attribute::getType, query.getType());
        w.eq(query.getCategoryId() != null, Attribute::getCategoryId, query.getCategoryId());
        String key = query.getKey();
        if (StringUtils.hasText(key))
            w.and(e -> e.eq(Attribute::getId, key).or().like(Attribute::getName, key));
        return (Page<AttributeDTO>) page(query.page(), w).convert(AttributeDTO::convertFor);
    }

    @Override
    public List<Attribute> InvertListByAttrGroupId(String key, Long attrGroupId) {
        // 查出属性组所属分类id
        Long categoryId = attrGroupService.getById(attrGroupId).getCategoryId();
        // 查出分类下所有规格属性
        // @formatter:off
        var attrIds = attrAttrGroupRelationService.getAttrIdsByAttrGroupId(attrGroupId);
        var wrapper = Wrappers.<Attribute>lambdaQuery()
                                                .eq(Attribute::getCategoryId, categoryId)
                                                .eq(Attribute::getType, AttrTypeEnum.SPU_TYPE);
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

    @Transactional
    @Override
    public void save(AttributeDTO attributeDTO) {
        var attribute=attributeDTO.convertToAttribute();
        save(attribute);
        var attrId=attribute.getId();
        if(attributeDTO.getAttrGroupId()!=null)
            attributeDTO.getAttrGroupId().forEach(attrGroupId->{
                attrAttrGroupRelationService.save(new AttrAttrGroupRelation().setAttrGroupId(attrGroupId).setAttrId(attrId));
            });
    }

    @Override
    public void updateById(AttributeDTO attributeDTO) {
        updateById(attributeDTO.convertToAttribute());
        if(attributeDTO.getAttrGroupId()!=null)
            attributeDTO.getAttrGroupId().forEach(attrGroupId->{
                attrAttrGroupRelationService.save(new AttrAttrGroupRelation().setAttrGroupId(attrGroupId).setAttrId(attributeDTO.getId()));
            });
    }

}
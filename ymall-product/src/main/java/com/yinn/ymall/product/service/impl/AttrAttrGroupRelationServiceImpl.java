package com.yinn.ymall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.AttrAttrGroupRelationDao;
import com.yinn.ymall.product.entity.AttrAttrGroupRelation;
import com.yinn.ymall.product.service.AttrAttrGroupRelationService;


@Service
public class AttrAttrGroupRelationServiceImpl extends ServiceImpl<AttrAttrGroupRelationDao, AttrAttrGroupRelation> implements AttrAttrGroupRelationService {

    @Override
    public List<Long> getAttrGroupIdsByAttrId(Long attrId) {
        // @formatter:off
        return list(Wrappers.<AttrAttrGroupRelation>lambdaQuery().eq(AttrAttrGroupRelation::getAttrId, attrId))
                    .stream()
                    .map(AttrAttrGroupRelation::getAttrGroupId)
                    .collect(Collectors.toList());
        // @formatter:on
    }

    @Override
    public void removeByAttrId(Long attrId) {
        // @formatter:off
        remove(
            Wrappers.<AttrAttrGroupRelation>lambdaQuery()
                .eq(AttrAttrGroupRelation::getAttrId, attrId)
        );
        // @formatter:on
    }

    @Override
    public void removeByAttrIds(List<Long> attrIds) {
        // @formatter:off
        remove(
            Wrappers.<AttrAttrGroupRelation>lambdaQuery()
                .in(AttrAttrGroupRelation::getAttrId, attrIds)
        );
        // @formatter:on
    }

    @Override
    public List<Long> getAttrIdsByAttrGroupId(Long attrGroupId) {
        // @formatter:off
        return list(Wrappers.<AttrAttrGroupRelation>lambdaQuery().eq(AttrAttrGroupRelation::getAttrGroupId, attrGroupId))
                    .stream()
                    .map(AttrAttrGroupRelation::getAttrGroupId)
                    .collect(Collectors.toList());
        // @formatter:on
    }

    @Override
    public void removeByAttrGroupId(Long attrGroupId) {
        // @formatter:off
        remove(
            Wrappers.<AttrAttrGroupRelation>lambdaQuery()
                .eq(AttrAttrGroupRelation::getAttrGroupId, attrGroupId)
        );
        // @formatter:on
    }

    @Override
    public void removeByAttrGroupIds(List<Long> attrGroupIds) {
        // @formatter:off
        remove(
            Wrappers.<AttrAttrGroupRelation>lambdaQuery()
                .in(AttrAttrGroupRelation::getAttrGroupId, attrGroupIds)
        );
        // @formatter:on
    }
}
package com.yinn.ymall.product.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.AttrAttrGroupRelationDao;
import com.yinn.ymall.product.entity.AttrAttrGroupRelation;
import com.yinn.ymall.product.service.AttrAttrGroupRelationService;


@Service
public class AttrAttrGroupRelationServiceImpl extends ServiceImpl<AttrAttrGroupRelationDao, AttrAttrGroupRelation> implements AttrAttrGroupRelationService {

    @Override
    public void remove(Long attrId, Long attrGroupId) {
        // @formatter:off
        remove(
            Wrappers.<AttrAttrGroupRelation>lambdaQuery()
                .eq(AttrAttrGroupRelation::getAttrId, attrId)
                .eq(AttrAttrGroupRelation::getAttrGroupId,attrGroupId)
        );
        // @formatter:on
    }
}
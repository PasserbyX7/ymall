package com.yinn.ymall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.SpuAttrValueDao;
import com.yinn.ymall.product.dto.SpuAttrValueDTO;
import com.yinn.ymall.product.entity.Attribute;
import com.yinn.ymall.product.entity.SpuAttrValue;
import com.yinn.ymall.product.service.AttributeService;
import com.yinn.ymall.product.service.SpuAttrValueService;

@Service
public class SpuAttrValueServiceImpl extends ServiceImpl<SpuAttrValueDao, SpuAttrValue> implements SpuAttrValueService {

    @Autowired
    private AttributeService attributeService;

    @Override
    public List<SpuAttrValue> listBySpuId(Long spuId) {
        return list(Wrappers.<SpuAttrValue>lambdaQuery().eq(SpuAttrValue::getSpuId, spuId));
    }

    @Override
    public void updateBatchBySpuId(Long spuId, List<SpuAttrValue> spuAttrValues) {
        // 移除所有旧值
        remove(Wrappers.<SpuAttrValue>lambdaQuery().eq(SpuAttrValue::getSpuId, spuId));
        // 保存新值
        saveBatch(spuAttrValues.stream().map(e -> e.setSpuId(spuId)).collect(Collectors.toList()));
    }

    @Override
    public List<SpuAttrValue> listSearchAttrBySpuId(Long spuId) {
        // 检索spu下所有规格属性
        var spuAttrValues = listBySpuId(spuId);
        // 将这些规格属性可被检索的id收集成集合
        var spuAttrIds = spuAttrValues.stream().map(SpuAttrValue::getAttrId).collect(Collectors.toList());
        // @formatter:off
        var searchAttrIdSet = attributeService
                                                .listSearchAttrsByIds(spuAttrIds)
                                                .stream()
                                                .map(Attribute::getId)
                                                .collect(Collectors.toSet());
        return spuAttrValues.stream()
                                                .filter(attr -> searchAttrIdSet.contains(attr.getAttrId()))// 过滤不可检索属性
                                                .collect(Collectors.toList());
        // @formatter:on
    }

    @Override
    public void saveBatch(List<SpuAttrValueDTO> spuAttrs, Long spuId) {
        // @formatter:off
        var spuAttrValues=spuAttrs.stream()
                                            .map(SpuAttrValueDTO::convertToSpuAttrValue)
                                            .map(e -> e.setSpuId(spuId))
                                            .map(e -> e.setAttrName(attributeService.getById(e.getAttrId()).getName()))
                                            .collect(Collectors.toList());
        // @formatter:on
        saveBatch(spuAttrValues);
    }

}
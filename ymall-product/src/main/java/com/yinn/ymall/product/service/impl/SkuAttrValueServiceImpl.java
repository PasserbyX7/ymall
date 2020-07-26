package com.yinn.ymall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.SkuAttrValueDao;
import com.yinn.ymall.product.dto.ItemDTO.SkuAttrDTO;
import com.yinn.ymall.product.entity.SkuAttrValue;
import com.yinn.ymall.product.service.SkuAttrValueService;

@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueDao, SkuAttrValue> implements SkuAttrValueService {

    @Override
    public List<SkuAttrDTO> listSkuAttrDTOBySpuId(Long spuId) {
        return baseMapper.listSkuAttrDTOBySpuId(spuId);
    }

    @Override
    public String listBySkuIdAsJson(Long skuId) {
        // @formatter:off
        var map=baseMapper.listBySkuIdAsStringList(skuId)
                                            .stream()
                                            .map(e->e.split(":"))
                                            .collect(Collectors.toMap(e->e[0], e->e[1]));
        // @formatter:on
        return JSON.toJSONString(map);
    }

}
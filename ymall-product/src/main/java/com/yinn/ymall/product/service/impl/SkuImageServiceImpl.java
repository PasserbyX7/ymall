package com.yinn.ymall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.SkuImageDao;
import com.yinn.ymall.product.entity.SkuImage;
import com.yinn.ymall.product.service.SkuImageService;

@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageDao, SkuImage> implements SkuImageService {

    @Override
    public List<SkuImage> getBySkuId(Long skuId) {
        return list(Wrappers.<SkuImage>lambdaQuery().eq(SkuImage::getSkuId, skuId));
    }
}
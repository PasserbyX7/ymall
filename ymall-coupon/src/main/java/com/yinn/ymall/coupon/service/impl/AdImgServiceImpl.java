package com.yinn.ymall.coupon.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.coupon.dao.AdImgDao;
import com.yinn.ymall.coupon.entity.AdImg;
import com.yinn.ymall.coupon.service.AdImgService;

@Service
public class AdImgServiceImpl extends ServiceImpl<AdImgDao, AdImg> implements AdImgService {

    @Cacheable(cacheNames = "AdImg::adCategoryId",key="#adCategoryId")
    @Override
    public List<AdImg> listByAdCategoryId(Long adCategoryId) {
        return list(Wrappers.<AdImg>lambdaQuery().eq(AdImg::getCategoryId,adCategoryId));
    }

}
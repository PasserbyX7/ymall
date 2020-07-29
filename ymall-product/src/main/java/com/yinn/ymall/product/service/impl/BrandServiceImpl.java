package com.yinn.ymall.product.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.product.dao.BrandDao;
import com.yinn.ymall.product.dto.BrandPageQueryDTO;
import com.yinn.ymall.product.entity.Brand;
import com.yinn.ymall.product.service.BrandService;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandDao, Brand> implements BrandService {

    @Override
    public Page<Brand> queryPage(BrandPageQueryDTO brandPageQueryDTO) {
        var w = Wrappers.<Brand>lambdaQuery();
        String key=brandPageQueryDTO.getKey();
        if (StringUtils.isNotBlank(key))
            w.and(e -> e.eq(Brand::getId, key).or().eq(Brand::getFirstLetter, key).or().like(Brand::getName, key));
        return page(brandPageQueryDTO.page(), w);
    }

    @Override
    public List<Brand> listByCategoryId(Long categoryId) {
        return baseMapper.listByCategoryId(categoryId);
    }
}
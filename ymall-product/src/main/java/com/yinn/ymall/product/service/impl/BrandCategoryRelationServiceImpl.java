package com.yinn.ymall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.BrandCategoryRelationDao;
import com.yinn.ymall.product.dto.BrandCategoryRelationDTO;
import com.yinn.ymall.product.entity.BrandCategoryRelation;
import com.yinn.ymall.product.service.BrandCategoryRelationService;
import com.yinn.ymall.product.service.BrandService;
import com.yinn.ymall.product.service.CategoryService;

@Service
public class BrandCategoryRelationServiceImpl extends ServiceImpl<BrandCategoryRelationDao, BrandCategoryRelation>
        implements BrandCategoryRelationService {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<BrandCategoryRelationDTO> listByBrandId(Long brandId) {
        // @formatter:off
        return list(Wrappers.<BrandCategoryRelation>lambdaQuery().eq(BrandCategoryRelation::getBrandId, brandId))
                            .stream()
                            .map(BrandCategoryRelationDTO::convertFor)
                            .map(e->e.setBrandName(brandService.getById(e.getBrandId()).getName()))
                            .map(e->e.setCategoryName(categoryService.getById(e.getCategoryId()).getName()))
                            .collect(Collectors.toList());
        // @formatter:on
    }
}
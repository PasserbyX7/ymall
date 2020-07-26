package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.dto.BrandCategoryRelationDTO;
import com.yinn.ymall.product.entity.BrandCategoryRelation;

/**
 * 品牌与分类的关联表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface BrandCategoryRelationService extends IService<BrandCategoryRelation> {

	List<BrandCategoryRelationDTO> listByBrandId(Long brandId);
}


package com.yinn.ymall.product.dao;

import com.yinn.ymall.product.entity.BrandCategoryRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌与分类的关联表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Mapper
public interface BrandCategoryRelationDao extends BaseMapper<BrandCategoryRelation> {
}

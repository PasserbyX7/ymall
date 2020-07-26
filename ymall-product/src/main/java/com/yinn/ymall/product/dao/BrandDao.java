package com.yinn.ymall.product.dao;

import com.yinn.ymall.product.entity.Brand;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 品牌表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Mapper
public interface BrandDao extends BaseMapper<Brand> {

    @Select("""
    SELECT
        b.*
    FROM
        pms_brand b
        LEFT JOIN pms_brand_category_relation bc ON bc.brand_id = b.id
    WHERE
        bc.category_id =#{categoryId}
""")
	List<Brand> listByCategoryId(Long categoryId);
}

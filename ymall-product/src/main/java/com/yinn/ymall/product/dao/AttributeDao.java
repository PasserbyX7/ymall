package com.yinn.ymall.product.dao;

import com.yinn.ymall.product.entity.Attribute;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 商品属性表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Mapper
public interface AttributeDao extends BaseMapper<Attribute> {

    @Select("""
    SELECT
        a.*
    FROM
        pms_attribute a
        LEFT JOIN pms_attr_attr_group_relation aar ON a.id = aar.attr_id
    WHERE
        aar.attr_group_id =#{attrGroupId}
    """)
	List<Attribute> listByAttrGroupId(Long attrGroupId);
}

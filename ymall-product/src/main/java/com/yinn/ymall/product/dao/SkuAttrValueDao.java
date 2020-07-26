package com.yinn.ymall.product.dao;

import com.yinn.ymall.product.dto.ItemDTO.SkuAttrDTO;
import com.yinn.ymall.product.entity.SkuAttrValue;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * sku属性值表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Mapper
public interface SkuAttrValueDao extends BaseMapper<SkuAttrValue> {

	List<SkuAttrDTO> listSkuAttrDTOBySpuId(@Param("spuId") Long spuId);

    @Select("""
    SELECT
        CONCAT( attr_name, ':', attr_value )
    FROM
        pms_sku_attr_value
    WHERE
        sku_id =#{skuId}
    """)
	List<String> listBySkuIdAsStringList(@Param("skuId") Long skuId);
}

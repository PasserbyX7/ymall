package com.yinn.ymall.product.dao;

import com.yinn.ymall.product.dto.ItemDTO.SpuAttrGroupDTO;
import com.yinn.ymall.product.entity.AttrGroup;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 属性分组表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroup> {

	List<SpuAttrGroupDTO> listSpuAttrGroupDTOs(Long spuId, Long categoryId);
}

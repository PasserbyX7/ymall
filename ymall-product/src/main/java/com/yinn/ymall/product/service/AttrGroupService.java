package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.dto.AttrGroupDTO;
import com.yinn.ymall.product.dto.AttrGroupQueryDTO;
import com.yinn.ymall.product.dto.ItemDTO.SpuAttrGroupDTO;
import com.yinn.ymall.product.entity.AttrGroup;

/**
 * 属性分组表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface AttrGroupService extends IService<AttrGroup> {

	Page<AttrGroup> queryPage(AttrGroupQueryDTO attrGroupQueryDTO);

    List<AttrGroupDTO> listByCategoryIdWithAttrs(Long categoryId);

    List<AttrGroup> listByCategoryId(Long categoryId);

	List<SpuAttrGroupDTO> listSpuAttrGroupDTOs(Long spuId, Long categoryId);
}


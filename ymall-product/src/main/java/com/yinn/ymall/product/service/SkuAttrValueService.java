package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.dto.ItemDTO.SkuAttrDTO;
import com.yinn.ymall.product.entity.SkuAttrValue;

/**
 * sku属性值表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
public interface SkuAttrValueService extends IService<SkuAttrValue> {

	List<SkuAttrDTO> listSkuAttrDTOBySpuId(Long spuId);

	String listBySkuIdAsJson(Long skuId);
}


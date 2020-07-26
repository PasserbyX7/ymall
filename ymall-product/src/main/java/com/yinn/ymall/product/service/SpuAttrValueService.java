package com.yinn.ymall.product.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.product.dto.SpuAttrValueDTO;
import com.yinn.ymall.product.entity.SpuAttrValue;

/**
 * spu属性值表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:50
 */
public interface SpuAttrValueService extends IService<SpuAttrValue> {

	List<SpuAttrValue> listBySpuId(Long spuId);

	void updateBatchBySpuId(Long spuId, List<SpuAttrValue> spuAttrValues);

	List<SpuAttrValue> listSearchAttrBySpuId(Long spuId);

	void saveBatch(List<SpuAttrValueDTO> spuAttrs, Long spuId);
}


package com.yinn.ymall.search.service;

import java.util.List;

import com.yinn.ymall.common.dto.SkuEsDTO;

public interface ProductService {
    void productUp(List<SkuEsDTO> skuEsList);

	void productDown(Long spuId);
}
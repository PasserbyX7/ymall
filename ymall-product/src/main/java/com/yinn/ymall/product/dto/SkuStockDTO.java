package com.yinn.ymall.product.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class SkuStockDTO {
	/**
	 * skuId
	 */
    @NonNull
	private Long skuId;
	/**
	 * 库存数
	 */
	private Integer stock=0;
	/**
	 * 库存锁定
	 */
	private Integer stockLock=0;
}
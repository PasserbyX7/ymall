package com.yinn.ymall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuStockDTO {
	/**
	 * skuId
	 */
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
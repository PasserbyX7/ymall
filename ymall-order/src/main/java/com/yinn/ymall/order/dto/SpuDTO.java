package com.yinn.ymall.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class SpuDTO implements Serializable {

    private static final long serialVersionUID = 2139215074866566917L;
    /**
     * 主键
     */
	private Long id;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 商品名
	 */
	private String name;
	/**
	 * 重量（默认为克）
	 */
	private BigDecimal weight;

}
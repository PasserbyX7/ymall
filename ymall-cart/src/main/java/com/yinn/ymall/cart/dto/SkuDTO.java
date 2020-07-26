package com.yinn.ymall.cart.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class SkuDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
	/**
	 * spuId
	 */
	private Long spuId;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 销量
	 */
	private Integer sale;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 副标题
	 */
	private String subtitle;
	/**
	 * 默认图片
	 */
	private String defaultImg;
}
package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品sku表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@Accessors(chain=true)
@TableName
public class Sku implements Serializable {

    private static final long serialVersionUID = -4267155882044786401L;
    /**
     * 主键
     */
	@TableId
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

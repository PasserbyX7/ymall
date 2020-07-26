package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * sku图集表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@Accessors(chain = true)
@TableName
public class SkuImage implements Serializable {

    private static final long serialVersionUID = -1886485352306915882L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * 图片url
	 */
	private String url;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 是否为默认图片
	 */
	private Boolean isDefault;

}

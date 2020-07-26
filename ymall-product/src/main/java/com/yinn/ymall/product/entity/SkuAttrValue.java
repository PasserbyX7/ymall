package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * sku属性值表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@Accessors(chain = true)
@TableName
public class SkuAttrValue implements Serializable {

    private static final long serialVersionUID = 7132482357805633783L;
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
	 * 属性id
	 */
	private Long attrId;
	/**
	 * 属性名
	 */
	private String attrName;
	/**
	 * 属性值
	 */
	private String attrValue;
	/**
	 * 排序
	 */
	private Integer sort;

}

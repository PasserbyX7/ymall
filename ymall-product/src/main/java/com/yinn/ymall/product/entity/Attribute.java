package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.constant.SearchTypeEnum;

import java.io.Serializable;

import lombok.Data;

/**
 * 商品属性表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@TableName
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1744712812894381218L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 所属分类id
	 */
	private Long categoryId;
	/**
	 * 属性名
	 */
	private String name;
	/**
	 * 属性类型：0->销售属性；1->规格属性
	 */
	private AttrTypeEnum type;
	/**
	 * 可选值列表（逗号分隔）
	 */
	private String optionalValueList;
	/**
	 * 检索类型：0->不检索；1->关键字检索
	 */
	private SearchTypeEnum searchType;
	/**
	 * 属性图标
	 */
	private String icon;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;

    /**
     * 属性对应属性组
     */
    @TableField(exist = false)
    private Long attrGroupId;
}

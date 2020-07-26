package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品分类表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@Accessors(chain = true)
@TableName
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 父分类id
	 */
	private Long parentId;
	/**
	 * 分类名
	 */
	private String name;
	/**
	 * 分类层级
	 */
	private Integer level;
	/**
	 * 是否显示
	 */
    @TableLogic(value = "1",delval = "0")
	private Boolean isShow;
	/**
	 * 是否显示在导航栏
	 */
	private Boolean isNav;
	/**
	 * 图标url
	 */
	private String icon;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 计量单位
	 */
	private String productUnit;
	/**
	 * 商品数量
	 */
	private Integer productCount;

    /**
     * 子节点
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//字段为空则不返回
    private List<Category>children=new ArrayList<>();
}

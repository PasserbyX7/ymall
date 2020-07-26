package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性分组表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@Accessors(chain = true)
@TableName
public class AttrGroup implements Serializable {

    private static final long serialVersionUID = 6977074538121623352L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 分组名
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 图标
	 */
	private String icon;
}

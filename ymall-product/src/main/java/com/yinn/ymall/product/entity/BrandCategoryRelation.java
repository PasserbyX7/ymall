package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 品牌与分类的关联表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@TableName
@Accessors(chain = true)
public class BrandCategoryRelation implements Serializable {

    private static final long serialVersionUID = -9178008211615063430L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 分类id
	 */
	private Long categoryId;

    /**
     * 品牌名
     */
    @TableField(exist = false)
    private String brandName;
    /**
     * 分类名
     */
    @TableField(exist = false)
    private String categoryName;
}

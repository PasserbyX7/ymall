package com.yinn.ymall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.experimental.Accessors;
import java.io.Serializable;
import lombok.Data;

/**
 * 广告表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-07-10 18:21:13
 */
@Data
@Accessors(chain = true)
@TableName
public class AdImg implements Serializable {

    private static final long serialVersionUID = -2710240433762063745L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 广告分类外键
	 */
	private Long categoryId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片
	 */
	private String pic;
	/**
	 * 图片链接
	 */
	private String url;
	/**
	 * 排序
	 */
	private Integer sort;

}

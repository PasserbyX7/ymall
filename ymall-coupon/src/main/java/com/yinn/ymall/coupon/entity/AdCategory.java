package com.yinn.ymall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.experimental.Accessors;
import java.io.Serializable;
import lombok.Data;

/**
 * 广告分类表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-07-10 18:21:13
 */
@Data
@Accessors(chain = true)
@TableName
public class AdCategory implements Serializable {

    private static final long serialVersionUID = -8446216876308175478L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 分类名
	 */
	private String name;

}

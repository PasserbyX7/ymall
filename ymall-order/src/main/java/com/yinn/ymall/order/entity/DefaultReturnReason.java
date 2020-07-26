package com.yinn.ymall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.experimental.Accessors;
import java.io.Serializable;
import lombok.Data;

/**
 * 订单预设退货理由表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Data
@Accessors(chain = true)
@TableName
public class DefaultReturnReason implements Serializable {

    private static final long serialVersionUID = -1494094082439743728L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 退货原因
	 */
	private String name;
	/**
	 * 排序字段
	 */
	private Integer sort;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;

}

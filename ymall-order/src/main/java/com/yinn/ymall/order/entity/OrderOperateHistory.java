package com.yinn.ymall.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.order.constant.OrderStatusEnum;

import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 订单操作历史记录
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Data
@Accessors(chain = true)
@TableName
public class OrderOperateHistory implements Serializable {

    private static final long serialVersionUID = -8241296157237435042L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 操作者
	 */
	private String operator;
	/**
	 * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
	 */
	private OrderStatusEnum orderStatus;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 操作时间
	 */
    @TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
}

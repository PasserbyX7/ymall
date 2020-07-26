package com.yinn.ymall.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * 支付信息表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Data
@Accessors(chain = true)
@TableName
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 7315744240072902779L;
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
	 * 订单号
	 */
    private String orderSn;
    /**
     * 订单名
     */
    private String orderName;
	/**
	 * 订单流水号
	 */
	private String tradeNo;
	/**
	 * 交易金额
	 */
	private BigDecimal payAmount;
	/**
	 * 交易内容
	 */
	private String content;
	/**
	 * 回调内容
	 */
	private String callbackContent;
	/**
	 * 回调时间
	 */
	private LocalDateTime callbackTime;
	/**
	 * 创建时间
	 */
    @TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	/**
	 * 确认时间
	 */
	private LocalDateTime confirmTime;
}

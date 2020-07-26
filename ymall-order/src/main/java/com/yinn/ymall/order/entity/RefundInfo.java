package com.yinn.ymall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.order.constant.RefundStatusEnum;

import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;

/**
 * 退款信息表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Data
@Accessors(chain = true)
@TableName
public class RefundInfo implements Serializable {

    private static final long serialVersionUID = -5120084547084986940L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 退款订单id
	 */
	private Long orderReturnId;
	/**
	 * 退款金额
	 */
	private BigDecimal refund;
	/**
	 * 退款交易流水号
	 */
	private String refundSn;
	/**
	 * 退款状态
	 */
	private RefundStatusEnum refundStatus;
	/**
	 * 退款内容
	 */
	private String refundContent;

}

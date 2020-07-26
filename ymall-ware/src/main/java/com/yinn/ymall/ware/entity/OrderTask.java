package com.yinn.ymall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.ware.constant.PaymentTypeEnum;
import com.yinn.ymall.ware.constant.OrderTaskStatusEnum;

import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 库存工单表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-08 01:19:23
 */
@Data
@Accessors(chain = true)
@TableName
public class OrderTask implements Serializable {

    private static final long serialVersionUID = -3387334171932668445L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * sku购买数量
	 */
	private Integer count;
	/**
	 * orderSn
	 */
	private String orderSn;
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 配送电话
	 */
	private String phone;
	/**
	 * 付款方式：0->在线付款；1->货到付款
	 */
	private PaymentTypeEnum paymentType;
	/**
	 * 工单状态
	 */
	private OrderTaskStatusEnum status;
	/**
	 * 物流单号
	 */
	private String trackingNo;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

}

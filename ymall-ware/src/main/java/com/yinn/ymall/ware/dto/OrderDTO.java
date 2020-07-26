package com.yinn.ymall.ware.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.yinn.ymall.ware.constant.OrderStatusEnum;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 6463810574462033143L;
    /**
     * 主键
     */
	private Long id;
	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 优惠卷id
	 */
	private Long couponId;
	/**
	 * 订单号
	 */
	private String orderSn;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 订单总额：订单项应付总额之和
	 */
	private BigDecimal totalAmount;
	/**
	 * 实付金额：订单总额+运费金额
	 */
	private BigDecimal payAmount;
	/**
	 * 运费金额
	 */
	private BigDecimal freightAmount;
	/**
	 * 促销抵扣金额
	 */
	private BigDecimal promotionAmount;
	/**
	 * 积分抵扣金额
	 */
	private BigDecimal integrationAmount;
	/**
	 * 优惠抵扣金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
	 */
	private OrderStatusEnum status;
	/**
	 * 物流公司
	 */
	private String deliverCompany;
	/**
	 * 物流单号
	 */
	private String deliverSn;
	/**
	 * 获得的积分
	 */
	private Integer integration;
	/**
	 * 获得的成长值
	 */
	private Integer growth;
	/**
	 * 发票类型：0->不开发票；1->电子发票；2->纸质发票
	 */
	private Integer billType;
	/**
	 * 自动确认时间（以天为单位）
	 */
	private Integer autoConfirmTime;
	/**
	 * 发票内容
	 */
	private String billContent;
	/**
	 * 收票人电话
	 */
	private String billReceiverPhone;
	/**
	 * 收票人邮箱
	 */
	private String billReceiverEmail;
	/**
	 * 收货人姓名
	 */
	private String receiverName;
	/**
	 * 收货人电话
	 */
	private String receiverPhone;
	/**
	 * 省份/直辖市
	 */
	private String receiverProvince;
	/**
	 * 城市
	 */
	private String receiverCity;
	/**
	 * 区域
	 */
	private String receiverRegion;
	/**
	 * 详细地址
	 */
	private String receiverDetailAddress;
	/**
	 * 邮编
	 */
	private String receiverPostCode;
	/**
	 * 订单备注
	 */
	private String note;
	/**
	 * 是否确认收货
	 */
	private Boolean isConfirm;
	/**
	 * 是否删除
	 */
	private Boolean isDelete;
	/**
	 * 下单时使用积分数
	 */
	private Integer userIntegration;
	/**
	 * 支付时间
	 */
	private LocalDateTime paymentTime;
	/**
	 * 发货时间
	 */
	private LocalDateTime deliveryTime;
	/**
	 * 确认收货时间
	 */
	private LocalDateTime confirmTime;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime modifyTime;

}
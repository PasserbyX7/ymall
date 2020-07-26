package com.yinn.ymall.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.order.constant.OrderStatusEnum;

import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * 订单表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Data
@Accessors(chain = true)
@TableName

public class Order implements Serializable {
    private static final long serialVersionUID = 6463810574462033143L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 会员id
	 */
	private Long memberId;
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
     * 运费金额
	 */
    private BigDecimal freightAmount;
    /**
     * 实付金额：订单总额+运费金额
     */
    private BigDecimal payAmount;
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
	 * 自动确认时间（以天为单位）
	 */
	private Integer autoConfirmTime;
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
    @TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

    public void setPayAmount(){
        payAmount=totalAmount.add(freightAmount);
    }

}

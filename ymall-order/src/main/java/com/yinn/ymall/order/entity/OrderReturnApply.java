package com.yinn.ymall.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.order.constant.OrderReturnApplyStatusEnum;

import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * 订单退货申请
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Data
@Accessors(chain = true)
@TableName
public class OrderReturnApply implements Serializable {

    private static final long serialVersionUID = -5986229947152906460L;
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
	 * 订单编号
	 */
	private String orderSn;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 退款金额
	 */
	private BigDecimal returnAmount;
	/**
	 * 退货人姓名
	 */
	private String returnName;
	/**
	 * 退货人电话
	 */
	private String returnPhone;
	/**
	 * 申请状态：0->待处理；1->已完成；2->已拒绝
	 */
	private OrderReturnApplyStatusEnum status;
	/**
	 * sku名
	 */
	private String skuName;
	/**
	 * sku图片
	 */
	private String skuImg;
	/**
	 * 销售属性（Json）
	 */
	private String skuAttrValues;
	/**
	 * sku价格
	 */
	private BigDecimal skuPrice;
	/**
	 * 实际支付价格
	 */
	private BigDecimal skuPayPrice;
	/**
	 * 退货数量
	 */
	private Integer count;
	/**
	 * 退货理由
	 */
	private String reason;
	/**
	 * 详细描述
	 */
	private String description;
	/**
	 * 描述图片（逗号分隔）
	 */
	private String descPics;
	/**
	 * 收货人
	 */
	private String receiver;
	/**
	 * 收货人电话
	 */
	private String receiverPhone;
	/**
	 * 收货人备注
	 */
	private String receiverNote;
	/**
	 * 收货时间
	 */
	private LocalDateTime receiveTime;
	/**
	 * 收货公司地址
	 */
	private String companyAddress;
	/**
	 * 处理人
	 */
	private String handler;
	/**
	 * 处理备注
	 */
	private String handlerNote;
	/**
	 * 处理时间
	 */
	private LocalDateTime handleTime;
	/**
	 * 创建时间
	 */
    @TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
}

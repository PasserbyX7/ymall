package com.yinn.ymall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;

/**
 * 会员信息统计表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
@Data
@TableName
public class MemberStatistics implements Serializable {

    private static final long serialVersionUID = -7818633518516484530L;
    /**
     * id
     */
	@TableId
	private Long id;
	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 累计消费金额
	 */
	private BigDecimal consumeAmount;
	/**
	 * 累计优惠金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 订单数
	 */
	private Integer orderCount;
	/**
	 * 评价数
	 */
	private Integer commentCount;
	/**
	 * 退货数
	 */
	private Integer returnCount;

}

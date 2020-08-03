package com.yinn.ymall.order.dto;

import java.io.Serializable;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.order.entity.Order;

import lombok.Data;

/**
 * 订单提交DTO 无需提交要购买的商品，直接在购物车进行查询
 *
 * @author Passerby
 */
@Data
public class OrderSubmitDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 用户名
     */
    private Long username;
    /**
     * 订单备注
     */
    private String note;
    /**
     * 收货地址id
     */
    private Long memberAddressId;
    /**
     * 防重令牌
     */
    private String orderToken;

    public Order convertToOrder() {
        return new OrderSubmitDTOConverter().doForward(this, Order.class);
    }

    private static class OrderSubmitDTOConverter implements Convert<OrderSubmitDTO, Order> {
    }

}
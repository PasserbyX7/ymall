package com.yinn.ymall.order.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 订单确认页所需数据
 *
 * @author Passerby
 * @since 1.0
 */
@Data
public class OrderConfirmDTO implements Serializable{

    private static final long serialVersionUID = 1460910182495508820L;
    /**
     * 收货地址列表
     */
    private List<MemberAddressDTO> addressList;
    /**
     * 订单项列表
     */
    private List<CartItemDTO>cartItemList;
    /**
     * 防重令牌
     */
    private String orderToken;
}
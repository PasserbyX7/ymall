package com.yinn.ymall.ware.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum{

    PENDING_PAYMENT(0,"待付款"),
    PENDING_SHIPPED(1,"待发货"),
    MERCHANT_SHIPPED(2,"已发货"),
    COMPLETE(3,"已完成"),
    CLOSED(4,"已关闭"),
    VOIDED(5,"无效");

    private final int value;

    private final String msg;

}
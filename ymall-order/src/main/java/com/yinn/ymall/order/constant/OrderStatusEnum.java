package com.yinn.ymall.order.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements IEnum<Integer>{
    /**
     * 待付款
     */
    PENDING_PAYMENT(0,"待付款"),
    /**
     * 待发货
     */
    PENDING_SHIPPED(1,"待发货"),
    /**
     * 已发送
     */
    MERCHANT_SHIPPED(2,"已发货"),
    /**
     * 已完成
     */
    COMPLETE(3,"已完成"),
    /**
     * 已关闭
     */
    CLOSED(4,"已关闭"),
    /**
     * 无效
     */
    VOIDED(5,"无效");

    private final int value;

    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }

}
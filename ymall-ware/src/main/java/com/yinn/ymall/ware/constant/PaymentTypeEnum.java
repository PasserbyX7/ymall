package com.yinn.ymall.ware.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeEnum implements IEnum<Integer>{

    PAY_ONLINE(0,"在线付款"),
    PAY_DELIVERY(1,"货到付款");

    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
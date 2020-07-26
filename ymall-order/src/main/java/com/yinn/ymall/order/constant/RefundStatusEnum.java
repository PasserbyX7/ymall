package com.yinn.ymall.order.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RefundStatusEnum implements IEnum<Integer>{

    UNDEFINED(0,"");

    private final int value;

    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
package com.yinn.ymall.order.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderReturnApplyStatusEnum implements IEnum<Integer> {

    PENDING_HANDLE(0,"待处理"),
    COMPLETE(1,"已完成"),
    REFUSE(2,"已拒绝");


    private final int value;

    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
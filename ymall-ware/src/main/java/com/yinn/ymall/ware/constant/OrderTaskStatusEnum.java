package com.yinn.ymall.ware.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTaskStatusEnum implements IEnum<Integer> {

    LOCKED(0, "已锁定"),
    UNLOCKED(1, "已解锁"),
    BUYING(2, "已付款");

    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
package com.yinn.ymall.member.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum implements IEnum<Integer> {
    female(0,"女"),male(1,"男");

    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
package com.yinn.ymall.member.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceTypeEnum implements IEnum<Integer> {
    SHOPPING(0,"购物获得"),ADMIN(1,"管理员修改"),PROMOTION(2,"活动获得");

    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
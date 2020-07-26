package com.yinn.ymall.product.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifyStatusEnum implements IEnum<Integer>{

    CREATE(0,"新建"),REVIEWED(1,"过审"),UNREVIEWED(2,"未过审");
    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
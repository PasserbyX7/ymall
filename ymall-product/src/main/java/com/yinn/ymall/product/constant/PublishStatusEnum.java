package com.yinn.ymall.product.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PublishStatusEnum implements IEnum<Integer>{

    CREATE(0,"新建"),UP(1,"上架"),DOWN(2,"下架");
    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
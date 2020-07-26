package com.yinn.ymall.product.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttrTypeEnum implements IEnum<Integer> {
    SKU_TYPE(0,"销售属性"), SPU_TYPE(1,"基本属性");
    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
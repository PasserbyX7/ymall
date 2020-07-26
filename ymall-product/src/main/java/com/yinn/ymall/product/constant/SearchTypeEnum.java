package com.yinn.ymall.product.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchTypeEnum implements IEnum<Integer>{
    NON_SEARCH(0,"不检索"),KEYWORD_SEARCH(1,"关键字检索");
    private final int value;
    private final String msg;

    @Override
    public Integer getValue() {
        return value;
    }
}
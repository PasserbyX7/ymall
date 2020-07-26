package com.yinn.ymall.common.utils;

import org.springframework.beans.BeanUtils;

public interface Convert<S, T> {

    default T doForward(S s, Class<T> clazz) {
        T t=null;
        try {
            t=clazz.getDeclaredConstructor().newInstance();
        }catch (Exception e) {
            throw new AssertionError("正向转化失败方法!");
        }
        BeanUtils.copyProperties(s, t);
        return t;
    }

    default S doBackward(T t,Class<S> clazz){
        S s=null;
        try {
            s=clazz.getDeclaredConstructor().newInstance();
        }catch (Exception e) {
            throw new AssertionError("逆向转化失败方法!");
        }
        BeanUtils.copyProperties(t, s);
        return s;
    }
}
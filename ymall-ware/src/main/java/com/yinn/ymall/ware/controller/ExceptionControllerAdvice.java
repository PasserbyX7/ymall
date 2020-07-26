package com.yinn.ymall.ware.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.ware.exception.StockShortageException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
* 统一异常处理类
* @author Passerby
* @since 1.0
*/
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(StockShortageException.class)
    public R<Void> handleException(StockShortageException e){
        log.error("库存锁定错误:{}",e.getMessage());
        return R.fail();
    }

}
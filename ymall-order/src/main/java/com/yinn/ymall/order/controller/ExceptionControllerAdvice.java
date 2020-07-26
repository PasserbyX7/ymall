package com.yinn.ymall.order.controller;

import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.order.exception.OrderSubmitException;

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

    @ExceptionHandler(OrderSubmitException.class)
    public R<Void> handleOrderSubmitException(OrderSubmitException e){
        log.error("订单提交异常：:{}",e.getMessage());
        return R.fail(ErrorCode.USER_REQUEST_SERVICE_ERROR);
    }

}
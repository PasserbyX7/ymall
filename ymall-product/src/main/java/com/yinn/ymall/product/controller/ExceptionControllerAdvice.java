package com.yinn.ymall.product.controller;

import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.exception.ProductDownException;
import com.yinn.ymall.common.exception.ProductUpException;
import com.yinn.ymall.common.exception.RPCException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(RPCException.class)
    public R<Void> handleException(RPCException e) {
        log.error("RPC调用异常：[{}]", e.getMessage());
        return R.fail(ErrorCode.RPC_RUNTIME_ERROR);
    }

    @ExceptionHandler(ProductUpException.class)
    public R<Void> handleException(ProductUpException e) {
        log.error("ES商品上架失败：[{}]", e.getMessage());
        return R.fail(ErrorCode.RPC_RUNTIME_ERROR);
    }

    @ExceptionHandler(ProductDownException.class)
    public R<Void> handleException(ProductDownException e) {
        log.error("ES商品下架失败：[{}]", e.getMessage());
        return R.fail(ErrorCode.RPC_RUNTIME_ERROR);
    }

}
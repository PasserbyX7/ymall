package com.yinn.ymall.search.controller;

import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.search.exception.EsSearchFailException;
import com.yinn.ymall.search.exception.ProductUpException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理类
 *
 * @author Passerby
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ProductUpException.class)
    public R<Void> handleException(ProductUpException e) {
        log.error("ES商品上架失败：[{}]", e.getMessage());
        return R.fail(ErrorCode.RPC_RUNTIME_ERROR);
    }

    @ExceptionHandler(EsSearchFailException.class)
    public R<Void> handleException(EsSearchFailException e) {
        log.error("ES搜索失败：[{}]", e.getMessage());
        return R.fail(ErrorCode.RPC_RUNTIME_ERROR);
    }

}

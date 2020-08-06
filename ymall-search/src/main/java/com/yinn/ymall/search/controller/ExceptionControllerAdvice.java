package com.yinn.ymall.search.controller;

import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.search.exception.EsSearchFailException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理类
 *
 * @author Passerby
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EsSearchFailException.class)
    public R<Void> handleException(EsSearchFailException e) {
        log.error("ES搜索失败：[{}]", e.getMessage());
        return R.fail(ErrorCode.RPC_RUNTIME_ERROR);
    }

}

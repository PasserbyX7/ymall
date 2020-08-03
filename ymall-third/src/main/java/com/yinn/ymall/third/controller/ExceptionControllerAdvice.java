package com.yinn.ymall.third.controller;

import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.exception.SmsSendException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
* 统一异常处理类
* @author Passerby
*/
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(SmsSendException.class)
    public R<Void> handleSmsSendException(SmsSendException e){
        return R.fail(ErrorCode.SMS_SEND_ERROR);
    }

}
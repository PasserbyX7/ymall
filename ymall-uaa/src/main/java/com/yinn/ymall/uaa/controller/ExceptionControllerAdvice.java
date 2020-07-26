package com.yinn.ymall.uaa.controller;

import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.exception.SmsSendException;
import com.yinn.ymall.uaa.exception.UserOAuthLoginException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

/**
* 统一异常处理类
* @author Passerby
* @since 1.0
*/
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(SmsSendException.class)
    public R<Void> handleSmsSendException(SmsSendException e){
        return R.fail(ErrorCode.USER_SMS_CODE_SEND_ERROR);
    }

    @ExceptionHandler(UserOAuthLoginException.class)
    public R<Void> handleUserOAuthLoginException(UserOAuthLoginException e){
        return R.fail(ErrorCode.USER_OAUTH_LOGIN_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public R<Void> handleHttpClientErrorException(HttpClientErrorException e){
        return R.fail(ErrorCode.USER_LOGIN_ERROR);
    }

}
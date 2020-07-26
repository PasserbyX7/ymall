package com.yinn.ymall.member.controller;

import com.yinn.ymall.common.api.ErrorCode;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.member.exception.OpenIdExistException;
import com.yinn.ymall.member.exception.PhoneExistException;
import com.yinn.ymall.member.exception.UsernameExistException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
* 统一异常处理类
* @author Passerby
* @since 1.0
*/
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(PhoneExistException.class)
    public R<Void> handlePhoneExistException(PhoneExistException e){
        return R.fail(ErrorCode.USER_PHONE_EXIST_ERROR);
    }

    @ExceptionHandler(UsernameExistException.class)
    public R<Void> handleUsernameExistException(UsernameExistException e){
        return R.fail(ErrorCode.USER_USERNAME_EXIST_ERROR);
    }

    @ExceptionHandler(OpenIdExistException.class)
    public R<Void> handleOpenIdExistException(OpenIdExistException e){
        return R.fail(ErrorCode.USER_OPENID_EXIST_ERROR);
    }

}
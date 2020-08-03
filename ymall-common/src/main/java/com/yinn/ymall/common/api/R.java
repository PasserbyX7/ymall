package com.yinn.ymall.common.api;

import java.io.Serializable;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * REST API 返回结果
 *
 * @author Passerby
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 734311540905656681L;

    /**
     * 默认错误码
     */
    private static final String DEFAULT_ERROR_CODE = ErrorCode.SYS_ERROR.getCode();

    /**
     * 错误码
     */
    private String code;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 结果集
     */
    private T data;

    public R(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public R(ErrorCode errorCode, T data) {
        errorCode = Optional.ofNullable(errorCode).orElse(ErrorCode.USER_ERROR);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    public boolean isOk() {
        return ErrorCode.SUCCESS.getCode().equals(code);
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(ErrorCode.SUCCESS, data);
    }

    public static <T> R<T> fail() {
        return fail("系统未知错误");
    }

    public static <T> R<T> fail(String msg) {
        return new R<T>(DEFAULT_ERROR_CODE, msg, null);
    }

    public static <T> R<T> fail(ErrorCode errorCode) {
        return fail(errorCode,null);
    }

    public static <T> R<T> fail(ErrorCode errorCode,T data) {
        return new R<T>(errorCode).setData(data);
    }

}

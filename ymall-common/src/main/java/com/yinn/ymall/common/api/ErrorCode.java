package com.yinn.ymall.common.api;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // @formatter:off
    SUCCESS("00000","执行成功"),

    USER_ERROR("A0001","用户端错误"),
    USER_REQUEST_PARAM_ERROR("A0400","用户请求参数错误"),
    USER_USERNAME_EXIST_ERROR("A0111","用户名已存在"),
    USER_OPENID_EXIST_ERROR("A0114","用户openId已存在"),
    USER_PHONE_EXIST_ERROR("A0115","用户手机号已存在"),
    USER_SMS_CODE_VALIDATE_ERROR("A0131","用户短信验证码校验错误"),
    USER_SMS_CODE_SEND_ERROR("A0134","用户短信验证码发送错误"),
    USER_LOGIN_ERROR("A200","用户登录错误"),
    USER_OAUTH_LOGIN_ERROR("A0223","用户第三方登录失败"),
    USER_REQUEST_SERVICE_ERROR("A0500","用户请求服务错误"),

    SYS_ERROR("B0001","系统执行错误"),

    THIRD_ERROR("C0001","第三方服务错误"),
    MIDDLEWARE_ERROR("C0100","中间件服务错误"),
    RPC_RUNTIME_ERROR("C0110","RPC服务错误"),
    SMS_SEND_ERROR("C0501","短信发送错误");
    // @formatter:on

    private final String code;
    private final String msg;

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }

    /**
     * 将code转换为ErrorCode，如果该code未定义，则返回SUCCESS
     *
     * @param code
     * @return: ErrorCode
     * @Date: 2020-05-02 05:35:25
     */
    public static ErrorCode fromCode(String code) {
        // @formatter:off
        return Stream.of(ErrorCode.values())
                                .filter(e->e.code.equals(code))
                                .findFirst()
                                .orElse(SUCCESS);
        // @formatter:on
    }

}
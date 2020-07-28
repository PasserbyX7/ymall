package com.yinn.ymall.uaa.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户注册DTO")
public class UserRegisterDTO {
    @ApiModelProperty("用户名（要求唯一）")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户手机号（要求唯一）")
    private String phone;
    @ApiModelProperty("用户短信验证码（仅可使用一次）")
    private String smsCode;
    @ApiModelProperty("微信小程序Code（仅可使用一次）")
    private String wxMiniCode;
    @ApiModelProperty("不必填写")
    private String openId;
}
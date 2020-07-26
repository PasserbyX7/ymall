package com.yinn.ymall.common.utils;

import com.alibaba.fastjson.JSON;
import com.yinn.ymall.common.dto.UserInfoDTO;

import org.springframework.security.oauth2.jwt.Jwt;

import net.minidev.json.JSONObject;

public class JwtUtils {

    public static final String GET_USER_INFO_EXPR= "T(com.yinn.ymall.common.utils.JwtUtils).getUserInfoDTO(#this)";
    public static final String GET_USER_ID_EXPR= GET_USER_INFO_EXPR+".getUserId()";
    public static final String GET_USER_OPENID_EXPR= GET_USER_INFO_EXPR+".getOpenId()";

    public static UserInfoDTO getUserInfoDTO(Jwt jwt){
        JSONObject json=(JSONObject)jwt.getClaim("UserInfo");
        return JSON.parseObject(json.toJSONString(),UserInfoDTO.class);
    }

}
package com.yinn.ymall.uaa.service;

import com.yinn.ymall.uaa.dto.WxMiniLoginDTO;

public interface WxMiniLoginService {
    String login(WxMiniLoginDTO user);
}
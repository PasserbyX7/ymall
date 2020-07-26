package com.yinn.ymall.uaa.service;

import com.yinn.ymall.uaa.dto.UsernameLoginDTO;

public interface UsernameLoginService {
    String login(UsernameLoginDTO user);
}
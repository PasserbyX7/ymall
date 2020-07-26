package com.yinn.ymall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.member.dto.UserDetailsDTO;
import com.yinn.ymall.member.dto.UserRegisterDTO;
import com.yinn.ymall.member.entity.Member;

/**
 * 会员表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
public interface MemberService extends IService<Member> {

    void register(UserRegisterDTO user);

	UserDetailsDTO getUserDetails(String username);

}


package com.yinn.ymall.member.dto;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.member.entity.Member;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRegisterDTO {

    private String username;
    private String password;
    private String phone;
    private String openId;

    public Member convertToMember() {
        return new UserRegisterDTOConverter().doForward(this, Member.class);
    }

    private static class UserRegisterDTOConverter implements Convert<UserRegisterDTO, Member> {

        @Override
        public Member doForward(UserRegisterDTO userRegisterDTO, Class<Member> clazz) {
            var password = new BCryptPasswordEncoder().encode(userRegisterDTO.getPassword());
            var member=new Member();
            BeanUtils.copyProperties(userRegisterDTO, member);
            return member.setPassword(password);
        }
    }

}
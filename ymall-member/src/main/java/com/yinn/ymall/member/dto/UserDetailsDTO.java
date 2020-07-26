package com.yinn.ymall.member.dto;

import java.io.Serializable;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.member.entity.Member;

import lombok.Data;

@Data
public class UserDetailsDTO implements Serializable{

    private static final long serialVersionUID = 1392060630611015495L;
    private Long id;
    private String openId;
    private String username;
    private String password;
    private Boolean isEnable=true;
    private Boolean isAccountNonExpired=true;
    private Boolean isCredentialsNonExpired=true;
    private String authorities;
    private Boolean isAccountNonLocked=true;

    public static UserDetailsDTO convertFor(Member member) {
        return new UserDetailsDTOConverter().doBackward(member, UserDetailsDTO.class);
    }

    private static class UserDetailsDTOConverter implements Convert<UserDetailsDTO, Member> {
    }

}
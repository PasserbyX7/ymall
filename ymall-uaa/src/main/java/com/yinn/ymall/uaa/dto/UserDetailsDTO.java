package com.yinn.ymall.uaa.dto;

import java.util.Collection;

import com.yinn.ymall.common.dto.UserInfoDTO;
import com.yinn.ymall.common.utils.Convert;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserDetailsDTO implements UserDetails{

    private static final long serialVersionUID = 1392060630611005495L;
    private Long id;
    private String openId;
    private String username;
    private String password;
    private Boolean isEnable;
    private Boolean isAccountNonExpired;
    private Boolean isCredentialsNonExpired;
    private String authorities;
    private Boolean isAccountNonLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }

    public UserInfoDTO convertToUserInfoDTO() {
        return new UserDetailsDTOConverter().doForward(this, null);
    }

    private static class UserDetailsDTOConverter implements Convert<UserDetailsDTO, UserInfoDTO> {

        @Override
        public UserInfoDTO doForward(UserDetailsDTO userDetailsDTO, Class<UserInfoDTO> clazz) {
            // @formatter:off
            return new UserInfoDTO()
                                .setUserId(userDetailsDTO.getId())
                                .setOpenId(userDetailsDTO.getOpenId());
            // @formatter:on
        }

    }

}
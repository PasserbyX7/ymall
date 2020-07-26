package com.yinn.ymall.member.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.member.dao.MemberDao;
import com.yinn.ymall.member.dto.UserDetailsDTO;
import com.yinn.ymall.member.dto.UserRegisterDTO;
import com.yinn.ymall.member.entity.Member;
import com.yinn.ymall.member.exception.OpenIdExistException;
import com.yinn.ymall.member.exception.PhoneExistException;
import com.yinn.ymall.member.exception.UsernameExistException;
import com.yinn.ymall.member.service.MemberService;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements MemberService {

    @Override
    public void register(UserRegisterDTO user) {
        checkUserRegisterDTO(user);
        save(user.convertToMember());
    }

    @Override
    public UserDetailsDTO getUserDetails(String username) {
        var member = getOne(
                Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username).or().eq(Member::getOpenId, username));
        var userDetailsDTO = UserDetailsDTO.convertFor(member);
        userDetailsDTO.setAuthorities("admin,user");// TODO 用户权限待查出
        return userDetailsDTO;
    }

    // 责任链模式
    private void checkUserRegisterDTO(UserRegisterDTO user) {
        checkPhoneUnique(user.getPhone());
        checkUsernameUnique(user.getUsername());
        checkOpenIdUnique(user.getOpenId());
    }

    private void checkOpenIdUnique(String openId) throws OpenIdExistException {
        var cnt = count(Wrappers.<Member>lambdaQuery().eq(Member::getOpenId, openId));
        if (cnt != 0)
            throw new OpenIdExistException();
    }

    private void checkUsernameUnique(String username) throws UsernameExistException {
        var cnt = count(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username));
        if (cnt != 0)
            throw new UsernameExistException();
    }

    private void checkPhoneUnique(String phone) throws PhoneExistException {
        var cnt = count(Wrappers.<Member>lambdaQuery().eq(Member::getPhone, phone));
        if (cnt != 0)
            throw new PhoneExistException();
    }

}
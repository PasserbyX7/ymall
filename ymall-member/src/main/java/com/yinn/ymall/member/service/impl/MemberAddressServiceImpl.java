package com.yinn.ymall.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.member.dao.MemberAddressDao;
import com.yinn.ymall.member.entity.MemberAddress;
import com.yinn.ymall.member.service.MemberAddressService;

@Service
public class MemberAddressServiceImpl extends ServiceImpl<MemberAddressDao, MemberAddress>
        implements MemberAddressService {

    @Override
    public List<MemberAddress> listByMemberId(Long memberId) {
        return list(Wrappers
                            .<MemberAddress>lambdaQuery()
                            .eq(MemberAddress::getMemberId, memberId));
    }
}
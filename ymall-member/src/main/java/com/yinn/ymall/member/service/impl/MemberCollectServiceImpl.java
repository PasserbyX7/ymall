package com.yinn.ymall.member.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.member.dao.MemberCollectDao;
import com.yinn.ymall.member.entity.MemberCollect;
import com.yinn.ymall.member.service.MemberCollectService;


@Service
public class MemberCollectServiceImpl extends ServiceImpl<MemberCollectDao, MemberCollect> implements MemberCollectService {
}
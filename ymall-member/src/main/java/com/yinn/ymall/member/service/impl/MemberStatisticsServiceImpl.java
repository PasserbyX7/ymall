package com.yinn.ymall.member.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.member.dao.MemberStatisticsDao;
import com.yinn.ymall.member.entity.MemberStatistics;
import com.yinn.ymall.member.service.MemberStatisticsService;


@Service
public class MemberStatisticsServiceImpl extends ServiceImpl<MemberStatisticsDao, MemberStatistics> implements MemberStatisticsService {
}
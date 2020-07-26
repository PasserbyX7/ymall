package com.yinn.ymall.member.dao;

import com.yinn.ymall.member.entity.MemberStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员信息统计表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
@Mapper
public interface MemberStatisticsDao extends BaseMapper<MemberStatistics> {
}

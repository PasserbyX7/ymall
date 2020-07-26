package com.yinn.ymall.member.dao;

import com.yinn.ymall.member.entity.MemberCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员所收藏的商品表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
@Mapper
public interface MemberCollectDao extends BaseMapper<MemberCollect> {
}

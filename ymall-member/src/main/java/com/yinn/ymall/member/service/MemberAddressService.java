package com.yinn.ymall.member.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.member.entity.MemberAddress;

/**
 * 收货地址表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
public interface MemberAddressService extends IService<MemberAddress> {

	List<MemberAddress> listByMemberId(Long memberId);
}


package com.yinn.ymall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 收货地址表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
@Data
@TableName
public class MemberAddress implements Serializable {

    private static final long serialVersionUID = 4851834726558473137L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 收货人姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 邮政编码
	 */
	private String postalCode;
	/**
	 * 省份/直辖市
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 省市区代码
	 */
	private String areaCode;
	/**
	 * 区
	 */
	private String region;
	/**
	 * 详细地址
	 */
	private String detailAddress;
	/**
	 * 是否默认
	 */
	private Boolean isDefault;

}

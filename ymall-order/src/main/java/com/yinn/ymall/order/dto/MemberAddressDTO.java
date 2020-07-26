package com.yinn.ymall.order.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberAddressDTO implements Serializable {

    private static final long serialVersionUID = 4851834726558473137L;
    /**
     * 主键
     */
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
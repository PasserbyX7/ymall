package com.yinn.ymall.member.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.member.constant.GenderEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 会员表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
@Data
@TableName
@Accessors(chain = true)
public class Member implements Serializable {

    private static final long serialVersionUID = -8970102707327448508L;
    /**
     * 主键
     */
	@TableId
    private Long id;
    /**
     * 微信openId
     */
    private String openId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 生日
	 */
	private LocalDate birthday;
	/**
	 * 头像
	 */
	private String header;
	/**
	 * 性别：0->女；1->男
	 */
	private GenderEnum gender;
	/**
	 * 是否启用
	 */
	private Boolean isEnable=true;
	/**
	 * 创建时间
	 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
	/**
	 * 个性签名
	 */
	private String sign;

}

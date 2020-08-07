package com.yinn.ymall.member.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.member.constant.GenderEnum;
import com.yinn.ymall.member.entity.Member;

import lombok.Data;

@Data
public class MemberDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
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
    private Boolean isEnable = true;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public static MemberDTO convertFrom(Member member) {
        return new MemberDTOConverter().doBackward(member, MemberDTO.class);
    }

    private static class MemberDTOConverter implements Convert<MemberDTO, Member> {
    }
}
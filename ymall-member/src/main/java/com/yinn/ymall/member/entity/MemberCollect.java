package com.yinn.ymall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 会员所收藏的商品表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-05 10:23:05
 */
@Data
@TableName
public class MemberCollect implements Serializable {

    private static final long serialVersionUID = 3550444009410745386L;
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
	 * spuId
	 */
	private Long spuId;
	/**
	 * 商品名
	 */
	private String spuName;
	/**
	 * 商品图片
	 */
	private String spuImg;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

}

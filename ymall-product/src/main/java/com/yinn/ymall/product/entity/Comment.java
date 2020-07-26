package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 商品评论表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@TableName
public class Comment implements Serializable {

    private static final long serialVersionUID = 5308835942709326323L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * spuId
	 */
	private Long spuId;
	/**
	 * 商品名
	 */
	private String spuName;
	/**
	 * 会员昵称
	 */
	private String memberNickName;
	/**
	 * 星级：0->5
	 */
	private Integer star;
	/**
	 * 是否显示
	 */
	private Boolean isShow;
	/**
	 * 回复数
	 */
	private Integer replayCount;
	/**
	 * 点赞数
	 */
	private Integer likeCount;
	/**
	 * 用户头像
	 */
	private String memberIcon;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
    @TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	/**
	 * 评论图片/视频（json）：{type:文件类型,url:文件地址}
	 */
	private String resources;

}

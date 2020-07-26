package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 评论回复关系表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@TableName
public class CommentReplay implements Serializable {

    private static final long serialVersionUID = 3040651956953376670L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 评论本身的id
	 */
	private Long commentId;
	/**
	 * 被回复的评论的id
	 */
	private Long replayId;

}

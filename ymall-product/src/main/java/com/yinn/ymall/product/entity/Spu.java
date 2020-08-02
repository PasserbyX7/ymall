package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.product.constant.PublishStatusEnum;
import com.yinn.ymall.product.constant.VerifyStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品spu表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:50
 */
@Data
@TableName
public class Spu implements Serializable {

    private static final long serialVersionUID = 2139215074866566917L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 商品名
	 */
	private String name;
	/**
	 * 文本描述
	 */
	private String textDesc;
	/**
	 * 图片描述
	 */
	private String imgDesc;
	/**
	 * 重量（默认为克）
	 */
	private BigDecimal weight;
	/**
	 * 是否推荐
	 */
	private Boolean isRecommend;
	/**
	 * 是否删除
	 */
	private Boolean isDelete;
	/**
	 * 上架状态：0->新建；1->上架；2->下架
	 */
    private PublishStatusEnum publishStatus;
    /**
     * 审核状态：0->新建；1->过审；2->未过审
     */
    private VerifyStatusEnum verifyStatus;
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

}

package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.common.validator.group.InsertGroup;
import com.yinn.ymall.common.validator.group.UpdateGroup;

import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 品牌表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@TableName
public class Brand implements Serializable {

    private static final long serialVersionUID = -4911660003402597957L;
    /**
     * 主键
     */
    @TableId
    @NotNull(message = "修改品牌必须指定id",groups = UpdateGroup.class)
	private Long id;
	/**
	 * 品牌名
	 */
    @NotBlank(message = "品牌名不能为空",groups = {InsertGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo
	 */
    @URL(message = "logo url不合法",groups = UpdateGroup.class)
	private String logo;
	/**
	 * 品牌首字母
	 */
	private String firstLetter;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 专区大图
	 */
	private String bigPic;
}

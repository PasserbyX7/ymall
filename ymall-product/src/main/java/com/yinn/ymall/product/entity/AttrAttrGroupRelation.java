package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 属性与属性分组的关系表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:51
 */
@Data
@Accessors(chain = true)
@TableName
public class AttrAttrGroupRelation implements Serializable {

    private static final long serialVersionUID = 4285741978372008504L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * 属性id
	 */
	private Long attrId;
	/**
	 * 属性组id
	 */
	private Long attrGroupId;
	/**
	 * 排序
	 */
	private Integer sort;

}

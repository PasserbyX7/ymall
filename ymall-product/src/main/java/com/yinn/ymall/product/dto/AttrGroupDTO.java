package com.yinn.ymall.product.dto;

import java.io.Serializable;
import java.util.List;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.product.entity.AttrGroup;
import com.yinn.ymall.product.entity.Attribute;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AttrGroupDTO implements Serializable {

    private static final long serialVersionUID = 6977074538120623352L;
    /**
     * 主键
     */
	private Long id;
	/**
	 * 分类id
	 */
	private Long categoryId;
	/**
	 * 分组名
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 图标
	 */
	private String icon;
    /**
     * 属性组下所有属性
     */
    private List<Attribute>Attributes;

    public static AttrGroupDTO convertFor(AttrGroup attrGroup) {
        return new AttrGroupDTOConverter().doBackward(attrGroup, AttrGroupDTO.class);
    }

    private static class AttrGroupDTOConverter implements Convert<AttrGroupDTO, AttrGroup> {
    }

}
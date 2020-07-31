package com.yinn.ymall.product.dto;

import java.io.Serializable;
import java.util.List;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.constant.SearchTypeEnum;
import com.yinn.ymall.product.entity.Attribute;
import com.yinn.ymall.product.service.AttrAttrGroupRelationService;
import com.yinn.ymall.product.service.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class AttributeDTO implements Serializable {

    private static final long serialVersionUID = 1744712802894381218L;
    /**
     * 主键
     */
	private Long id;
	/**
	 * 所属分类id
	 */
	private Long categoryId;
	/**
	 * 属性名
	 */
	private String name;
	/**
	 * 属性类型：0->销售属性；1->规格属性
	 */
	private AttrTypeEnum type;
	/**
	 * 可选值列表（逗号分隔）
	 */
	private String optionalValueList;
	/**
	 * 检索类型：0->不检索；1->关键字检索
	 */
	private SearchTypeEnum searchType;
	/**
	 * 属性图标
	 */
	private String icon;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
    /**
     * 属性对应属性组
     */
    private List<Long> attrGroupId;
    /**
     * 分类路径
     */
    private List<Long> categoryPath;

    public static AttributeDTO convertFor(Attribute Attribute) {
        return new AttributeDTOConverter().doBackward(Attribute,null);
    }

    private static class AttributeDTOConverter implements Convert<AttributeDTO, Attribute> {

        @Autowired
        private AttrAttrGroupRelationService attrAttrGroupRelationService;

        @Autowired
        private CategoryService categoryService;

        @Override
        public AttributeDTO doBackward(Attribute attribute, Class<AttributeDTO> clazz) {
            var attributeDTO=new AttributeDTO();
            BeanUtils.copyProperties(attributeDTO, attribute);
            attributeDTO.setAttrGroupId(attrAttrGroupRelationService.getAttrGroupIdsByAttrId(attribute.getId()));
            attributeDTO.setCategoryPath(categoryService.getCategoryPath(attribute.getCategoryId()));
            return attributeDTO;
        }

    }
}
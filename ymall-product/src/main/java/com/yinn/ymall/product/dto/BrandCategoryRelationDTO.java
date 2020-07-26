package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.product.entity.BrandCategoryRelation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BrandCategoryRelationDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 分类id
	 */
	private Long categoryId;

    /**
     * 品牌名
     */
    private String brandName;
    /**
     * 分类名
     */
    private String categoryName;

    public static BrandCategoryRelationDTO convertFor(BrandCategoryRelation brandCategoryRelation) {
        return new BrandCategoryRelationDTOConverter().doBackward(brandCategoryRelation, BrandCategoryRelationDTO.class);
    }

    private static class BrandCategoryRelationDTOConverter implements Convert<BrandCategoryRelationDTO, BrandCategoryRelation> {
    }
}
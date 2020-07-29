package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.PageQueryDTO;
import com.yinn.ymall.product.entity.Sku;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SkuPageQueryDTO extends PageQueryDTO<Sku> implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 价格下限
     */
    private Integer min;
    /**
     * 价格上限
     */
    private Integer max;
    /**
     * 查询关键字
     */
    private String key;
}
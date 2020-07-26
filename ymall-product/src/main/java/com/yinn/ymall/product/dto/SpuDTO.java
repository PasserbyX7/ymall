package com.yinn.ymall.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.product.entity.Spu;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpuDTO {
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
     * 商品文本描述
     */
    private String textDesc;
    /**
     * 商品图片描述（以逗号分隔）
     */
    private String ImgDesc;
    /**
     * 重量（默认为克）
     */
    private BigDecimal weight;
    /**
     * 规格属性列表
     */
    private List<SpuAttrValueDTO> spuAttrs;
    /**
     * SkuDTO列表
     */
    private List<SkuDTO> skuDTOs;

    public Spu convertToSpu() {
        return new SpuDTOConverter().doForward(this, Spu.class);
    }

    private static class SpuDTOConverter implements Convert<SpuDTO, Spu> {
    }
}
package com.yinn.ymall.product.dto;

import java.util.List;

import com.yinn.ymall.product.entity.Sku;
import com.yinn.ymall.product.entity.SkuImage;

import lombok.Data;

@Data
public class ItemDTO {
    /**
     * 基本sku信息
     */
    private Sku sku;
    /**
     * sku图集
     */
    List<SkuImage> images;
    /**
     * 商品图片描述信息，逗号分隔
     */
    String imgDesc;
    /**
     * 销售属性列表
     */
    List<SkuAttrDTO> SkuAttrDTOs;
    /**
     * 规格属性组列表
     */
    List<SpuAttrGroupDTO> SpuAttrGroupDTOs;

    @Data
    public static class SkuAttrDTO {
        private Long attrId;
        private String attrName;
        private List<SkuAttrValueWithSkuIdDTO> attrValues;
    }

    @Data
    public static class SpuAttrGroupDTO {
        private String groupName;
        private List<SpuAttrDTO> attrs;
    }

    @Data
    public static class SpuAttrDTO {
        private String attrName;
        private String attrValues;
    }

    @Data
    public static class SkuAttrValueWithSkuIdDTO {
        private String attrValue;
        private String skuIds;
    }
}
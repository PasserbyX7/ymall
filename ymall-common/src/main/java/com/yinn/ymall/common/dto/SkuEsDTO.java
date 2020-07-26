package com.yinn.ymall.common.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SkuEsDTO {
    private Long skuId;
    private Long spuId;
    private Long brandId;
    private String brandName;
    private String brandImg;
    private Long categoryId;
    private String categoryName;
    private String title;
    private BigDecimal price;
    private String skuImg;
    private Long sale;
    private Boolean hasStock;
    private Long hotScore;
    private List<AttrEsDTO>attrs;
    @Data
    public static class AttrEsDTO{
        private Long attrId;
        private String attrName;
        private String attrValue;
    }
}
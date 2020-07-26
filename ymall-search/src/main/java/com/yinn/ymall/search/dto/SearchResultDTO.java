package com.yinn.ymall.search.dto;

import java.util.List;

import com.yinn.ymall.common.dto.SkuEsDTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class SearchResultDTO {
    /**
     * 商品信息
     */
    private List<SkuEsDTO> products;
    /**
     * 品牌相关信息
     */
    private List<BrandDTO> brands;
    /**
     * 分类相关信息
     */
    private List<CategoryDTO> categories;
    /**
     * 属性相关信息
     */
    private List<AttrDTO> attrs;
    /**
     * 分页信息
     */
    private PageDTO page;

    @Data
    public static class BrandDTO {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class AttrDTO {
        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }

    @Data
    public static class CategoryDTO {
        private Long categoryId;
        private String categoryName;
    }

    @Data
    @Accessors(chain = true)
    public static class PageDTO {
        /**
         * 总数
         */
        private long total;
        /**
         * 每页显示条数
         */
        private long size;

        /**
         * 当前页
         */
        private long current;
    }
}
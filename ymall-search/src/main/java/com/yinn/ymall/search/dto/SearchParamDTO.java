package com.yinn.ymall.search.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检索条件分析：
 *      全文检索：keyword
 *      排序：销量、热度、价格
 *      过滤：库存、价格区间、品牌、分类、属性
 */
@ApiModel("查询参数DTO")
@Data
public class SearchParamDTO {
    /**
     * 全文匹配关键字
     */
    @ApiModelProperty("全文匹配关键字")
    private String keyword;
    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Long categoryId;
    /**
     * 排序
     * sale-asc/desc
     * price-asc/desc
     * hotScore-asc/desc
     */
    @ApiModelProperty(value="排序",example = "sale-asc/desc或price-asc/desc或hotScore-asc/desc")
    private String sort;
    /**
     * 过滤
     */
    private Boolean hasStock;
    @ApiModelProperty(value = "价格区间",example = "100-1000")
    private String price;//格式：low-high
    private List<Long> brandId;
    @ApiModelProperty(value="属性值列表",example = "属性id:属性值1-属性值2")
    private List<String> attrs;//格式：id:value1-value2
    /**
     * 分页
     */
    @ApiModelProperty("当前页码")
    private Integer page;//当前页码
}
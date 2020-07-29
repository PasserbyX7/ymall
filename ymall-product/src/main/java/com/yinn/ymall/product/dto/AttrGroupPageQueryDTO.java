package com.yinn.ymall.product.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import com.yinn.ymall.common.dto.PageQueryDTO;
import com.yinn.ymall.product.entity.AttrGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel("属性组分页查询DTO")
public class AttrGroupPageQueryDTO extends PageQueryDTO<AttrGroup> implements Serializable{

    private static final long serialVersionUID = 8691453738345141942L;

    @ApiModelProperty("查询关键字：匹配分类id或属性组名")
    private String key;

    @ApiModelProperty(value="分类id",example = "1")
    private Long categoryId;

}
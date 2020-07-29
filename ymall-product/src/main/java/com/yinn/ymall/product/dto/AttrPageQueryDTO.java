package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.PageQueryDTO;
import com.yinn.ymall.product.constant.AttrTypeEnum;
import com.yinn.ymall.product.entity.Attribute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel("属性组分页查询DTO")
public class AttrPageQueryDTO extends PageQueryDTO<Attribute> implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("查询关键字：匹配id或名称（模糊匹配）")
    private String key;

    @ApiModelProperty("属性类型")
    private AttrTypeEnum type;
}
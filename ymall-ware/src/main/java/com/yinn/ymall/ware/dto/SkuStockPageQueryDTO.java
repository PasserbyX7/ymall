package com.yinn.ymall.ware.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.PageQueryDTO;
import com.yinn.ymall.ware.entity.SkuStock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel("属性组分页查询DTO")
public class SkuStockPageQueryDTO extends PageQueryDTO<SkuStock> implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("匹配id")
    private Long id;

    @ApiModelProperty("匹配skuId")
    private Long skuId;

}
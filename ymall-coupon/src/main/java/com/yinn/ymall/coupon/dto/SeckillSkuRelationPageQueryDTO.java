package com.yinn.ymall.coupon.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.PageQueryDTO;
import com.yinn.ymall.coupon.entity.SeckillSkuRelation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel("秒杀商品关联分页查询DTO")
public class SeckillSkuRelationPageQueryDTO extends PageQueryDTO<SeckillSkuRelation> implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类id", example = "1")
    private Long sessionId;
}
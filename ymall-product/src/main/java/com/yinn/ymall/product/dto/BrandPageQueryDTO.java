package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.PageQueryDTO;
import com.yinn.ymall.product.entity.Brand;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel("品牌分页查询DTO")
public class BrandPageQueryDTO extends PageQueryDTO<Brand> implements Serializable{
    private static final long serialVersionUID = -4707348042845021508L;
    private String key;
}
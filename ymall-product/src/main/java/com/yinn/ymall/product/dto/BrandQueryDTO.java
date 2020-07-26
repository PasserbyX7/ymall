package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.QueryDTO;
import com.yinn.ymall.product.entity.Brand;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BrandQueryDTO extends QueryDTO<Brand> implements Serializable{

    private static final long serialVersionUID = 1L;
    String key;
}
package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.PageQueryDTO;
import com.yinn.ymall.product.constant.PublishStatusEnum;
import com.yinn.ymall.product.entity.Spu;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SpuPageQueryDTO extends PageQueryDTO<Spu> implements Serializable{

    private static final long serialVersionUID = 3719530212447468238L;
    private Long categoryId;
    private Long brandId;
    private PublishStatusEnum publishStatus;
    private String key;

}
package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.dto.QueryDTO;
import com.yinn.ymall.product.entity.AttrGroup;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class AttrGroupQueryDTO extends QueryDTO<AttrGroup> implements Serializable{

    private static final long serialVersionUID = 8691453738345141942L;

    private String key;

    private Long categoryId;

}
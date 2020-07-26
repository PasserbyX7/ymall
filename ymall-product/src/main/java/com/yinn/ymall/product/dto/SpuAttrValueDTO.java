package com.yinn.ymall.product.dto;

import java.io.Serializable;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.product.entity.SpuAttrValue;

import lombok.Data;

@Data
public class SpuAttrValueDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性值
     */
    private String attrValue;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否显示
     */
    private Boolean isShow;

    public SpuAttrValue convertToSpuAttrValue() {
        return new SpuAttrValueDTOConverter().doForward(this, SpuAttrValue.class);
    }

    private static class SpuAttrValueDTOConverter implements Convert<SpuAttrValueDTO, SpuAttrValue> {
    }
}
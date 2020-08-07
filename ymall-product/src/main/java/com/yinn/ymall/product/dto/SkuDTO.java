package com.yinn.ymall.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.product.entity.Sku;
import com.yinn.ymall.product.entity.SkuAttrValue;
import com.yinn.ymall.product.entity.SkuImage;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SkuDTO {
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 对应图集
     */
    private List<SkuImage> images;
    /**
     * 销售属性
     */
    private List<SkuAttrValue> skuAttrs;
    /**
     * 库存数
     */
    private Integer stock = 0;
    public Sku convertToSku() {
        return new SkuDTOConverter().doForward(this, Sku.class);
    }

    private static class SkuDTOConverter implements Convert<SkuDTO, Sku> {
    }

}
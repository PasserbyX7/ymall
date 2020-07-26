package com.yinn.ymall.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartItemDTO implements Serializable {

    private static final long serialVersionUID = -464565274771464844L;
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 商品是否被选中
     */
    private Boolean isCheck;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 商品销售属性信息，形如：{"颜色":"蓝","尺寸":"大"}
     */
    private String attrs;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 是否有货
     */
    private Boolean isHasStock;
}
package com.yinn.ymall.cart.dto;

import com.yinn.ymall.cart.entity.CartItem;
import com.yinn.ymall.common.utils.Convert;

import lombok.Data;

@Data
public class CartItemDTO {
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 商品是否被选中
     */
    private Boolean isCheck;

    public CartItem convertToCartItem() {
        return new CartItemDTOConvert().doForward(this, CartItem.class);
    }

    private static class CartItemDTOConvert implements Convert<CartItemDTO, CartItem> {
    }

}
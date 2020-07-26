package com.yinn.ymall.cart.service;

import java.util.List;

import com.yinn.ymall.cart.dto.CartItemDTO;
import com.yinn.ymall.cart.entity.CartItem;

public interface CartItemService {

    /**
     * 本地查询cartItem
     *
     * @param skuId   skuId
     * @param cartKey cartKey
     * @Date: 2020-05-25 00:00:38
     */
    CartItem getBySkuId(Long skuId, String cartKey);

    /**
     * 远程查询并保存cartItem
     *
     * @Date: 2020-05-24 23:54:21
     */
    void save(CartItemDTO cartItemDTO, String cartKey);

    /**
     * 本地更新cartItem
     *
     * @Date: 2020-05-25 00:00:38
     */
    void update(CartItemDTO cartItemDTO, String cartKey);

    /**
     * 本地删除cartItem
     *
     * @param skuId   skuId
     * @param cartKey cartKey
     * @Date: 2020-05-25 00:00:38
     */
    void removeBySkuId(Long skuId, String cartKey);

    /**
     * 列出本地所有购物车项
     *
     * @Date: 2020-05-25 00:07:57
     */
    List<CartItem> list(String cartKey);

    /**
     * 列出所有最新已选中购物车项
     *
     * @Date: 2020-05-25 00:07:57
     */
    List<CartItem> listSelected(String cartKey);

}
package com.yinn.ymall.cart.entity;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 购物车在Redis的数据结构
 *      key：购物车id
 *      value：哈希表
 *          key：商品skuId
 *          value：购物项
 * 总结：Map<String,Map<String,Item>>
 *
 * @author Passerby
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class Cart {
    /**
     * 商品项列表
     */
    List<CartItem> items;
}
package com.yinn.ymall.cart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.yinn.ymall.cart.dto.CartItemDTO;
import com.yinn.ymall.cart.entity.CartItem;
import com.yinn.ymall.cart.feign.ProductFeignService;
import com.yinn.ymall.cart.feign.WareFeignService;
import com.yinn.ymall.cart.service.CartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private WareFeignService wareFeignService;

    @Override
    public void removeBySkuId(Long skuId, String cartKey) {
        getCartOps(cartKey).delete(skuId.toString());
    }

    /**
     * @param skuId   skuId
     * @param cartKey 购物车对应key
     * @Date: 2020-05-19 10:58:49
     */
    @Override
    public CartItem getBySkuId(Long skuId, String cartKey) {
        var ops = getCartOps(cartKey);
        String json=(String) ops.get(skuId.toString());
        return JSON.parseObject(json, CartItem.class);
    }

    @Override
    public void save(CartItemDTO cartItemDTO, String cartKey) {
        var skuId = cartItemDTO.getSkuId();
        var skuDTO = productFeignService.getById(skuId).getData();
        var attrs = productFeignService.listBySkuIdAsStringList(skuId).getData();
        var isHasStock=wareFeignService.getSkuHasStock(skuId).getData();
        // @formatter:off
        var cartItem=new CartItem()
                                .setSkuId(skuDTO.getId())
                                .setCount(cartItemDTO.getCount())
                                .setImage(skuDTO.getDefaultImg())
                                .setTitle(skuDTO.getTitle())
                                .setPrice(skuDTO.getPrice())
                                .setIsCheck(true)
                                .setAttrs(attrs)
                                .setIsHasStock(isHasStock);
        // @formatter:on
        save(cartItem, cartKey);
    }

    @Override
    public void update(CartItemDTO cartItemDTO, String cartKey) {
        // @formatter:off
        var cartItem=getBySkuId(cartItemDTO.getSkuId(), cartKey)
                                    .setIsCheck(cartItemDTO.getIsCheck())
                                    .setCount(cartItemDTO.getCount());
        // @formatter:on
        save(cartItem, cartKey);
    }

    /**
     * 获取缓存中购物项列表（不进行价格校对）
     *
     * @param cartKey 购物车key
     * @Date: 2020-05-20 13:18:40
     */
    @Override
    public List<CartItem> list(String cartKey) {
        // @formatter:off
        return getCartOps(cartKey)
                        .values()
                        .stream()
                        .map(String::valueOf)
                        .map(json -> JSON.parseObject(json, CartItem.class))
                        .collect(Collectors.toList());
        // @formatter:on
    }

    /**
     * 获取缓存中已选中的购物项列表（进行比价和比库存操作）
     *
     * @param cartKey 购物车key
     * @Date: 2020-05-20 13:18:40
     */
    @Override
    public List<CartItem> listSelected(String cartKey) {
        // @formatter:off
        return list(cartKey)
                        .stream()
                        .filter(CartItem::getIsCheck)
                        .map(item->item.setPrice(productFeignService.getSkuPriceById(item.getSkuId()).getData()))
                        .map(item->item.setIsHasStock(wareFeignService.getSkuHasStock(item.getSkuId()).getData()))
                        .collect(Collectors.toList());
        // @formatter:on
    }

    private void save(CartItem cartItem, String cartKey) {
        getCartOps(cartKey).put(cartItem.getSkuId().toString(), JSON.toJSONString(cartItem));
    }

    private BoundHashOperations<String, Object, Object> getCartOps(String cartKey) {
        return template.boundHashOps(cartKey);
    }

}
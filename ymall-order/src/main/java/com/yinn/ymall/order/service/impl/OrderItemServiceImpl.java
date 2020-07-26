package com.yinn.ymall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.order.dao.OrderItemDao;
import com.yinn.ymall.order.dto.CartItemDTO;
import com.yinn.ymall.order.entity.OrderItem;
import com.yinn.ymall.order.feign.ProductFeignService;
import com.yinn.ymall.order.service.OrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItem> implements OrderItemService {

    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public OrderItem getOrderItem(CartItemDTO cartItem) {
        var orderItem = new OrderItem();
        // 设置sku信息
        // @formatter:off
        orderItem.setSkuId(cartItem.getSkuId())
                        .setSkuName(cartItem.getTitle())
                        .setSkuPic(cartItem.getImage())
                        .setSkuPrice(cartItem.getPrice())
                        .setCount(cartItem.getCount())
                        .setSkuAttrValues(cartItem.getAttrs());
        // @formatter:on
        // 设置spu信息
        var spu = productFeignService.getSpuBySkuId(cartItem.getSkuId()).getData();
        // @formatter:off
        orderItem.setSpuId(spu.getId())
                        .setCategoryId(spu.getCategoryId())
                        .setSpuName(spu.getName());
        // @formatter:on
        //设置应付总额信息
        orderItem.setPayAmount();
        return orderItem;
    }

}
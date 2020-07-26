package com.yinn.ymall.cart.service.impl;

import com.yinn.ymall.cart.entity.Cart;
import com.yinn.ymall.cart.service.CartItemService;
import com.yinn.ymall.cart.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemService cartItemService;

    @Override
    public Cart getByKey(String cartKey) {
        return new Cart().setItems(cartItemService.list(cartKey));
    }

}
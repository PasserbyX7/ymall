package com.yinn.ymall.cart.service;

import com.yinn.ymall.cart.entity.Cart;

public interface CartService {

	Cart getByKey(String cartKey);

}
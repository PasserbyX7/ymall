package com.yinn.ymall.cart.controller;

import com.yinn.ymall.cart.constant.RedisConstant;
import com.yinn.ymall.cart.entity.Cart;
import com.yinn.ymall.cart.service.CartService;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "购物车接口")
@RestController
@RequestMapping("/api/cart/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation("获取购物车")
    @GetMapping
    public R<Cart> get(@AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long id){
        String cartKey=RedisConstant.CART_PRE+id;
        return R.ok(cartService.getByKey(cartKey));
    }

}
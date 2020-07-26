package com.yinn.ymall.cart.controller;

import java.util.List;

import com.yinn.ymall.cart.constant.RedisConstant;
import com.yinn.ymall.cart.dto.CartItemDTO;
import com.yinn.ymall.cart.entity.CartItem;
import com.yinn.ymall.cart.service.CartItemService;
import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "购物项接口")
@RestController
@RequestMapping("/api/cart/v1/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartService;

    @ApiOperation("添加商品项")
    @PostMapping
    public R<Void> save(CartItemDTO cartItemDTO,@AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long id){
        String cartKey=RedisConstant.CART_PRE+id;
        cartService.save(cartItemDTO, cartKey);
        return R.ok();
    }

    @ApiOperation("更新商品项")
    @PutMapping
    public R<Void> update(CartItemDTO cartItemDTO,@AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long id){
        String cartKey=RedisConstant.CART_PRE+id;
        cartService.update(cartItemDTO, cartKey);
        return R.ok();
    }

    @ApiOperation("删除商品项")
    @DeleteMapping("/skus/{skuId}")
    public R<Void> delete(@PathVariable Long skuId,
        @AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long id){
        String cartKey=RedisConstant.CART_PRE+id;
        cartService.removeBySkuId(skuId,cartKey);
        return R.ok();
    }

    @ApiOperation("列出当前用户已选中的购物项")
    @GetMapping("/selected")
    public R<List<CartItem>> listSelected(@AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long id){
        String cartKey=RedisConstant.CART_PRE+id;
        return R.ok(cartService.listSelected(cartKey));
    }

}
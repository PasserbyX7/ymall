package com.yinn.ymall.order.feign;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.order.dto.CartItemDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("cart-service")
public interface CartFeignService {

    @GetMapping("/api/cart/v1/cart-items/selected")
    R<List<CartItemDTO>> listSelectedCartItem();

}
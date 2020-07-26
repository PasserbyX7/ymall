package com.yinn.ymall.cart.feign;

import java.math.BigDecimal;

import com.yinn.ymall.cart.dto.SkuDTO;
import com.yinn.ymall.common.api.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductFeignService {

    @GetMapping("/api/product/v1/skus/{skuId}")
    R<SkuDTO>getById(@PathVariable Long skuId);

    @GetMapping("/api/product/v1/sku-attr-values/skus/{skuId}/json")
    R<String> listBySkuIdAsStringList(@PathVariable Long skuId);

    @GetMapping("/api/product/v1/skus/{skuId}/price")
    R<BigDecimal>getSkuPriceById(@PathVariable Long skuId);
}

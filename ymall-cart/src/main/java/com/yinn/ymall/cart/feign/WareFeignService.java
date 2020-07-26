package com.yinn.ymall.cart.feign;

import com.yinn.ymall.common.api.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ware-service")
public interface WareFeignService {

    @GetMapping("/api/ware/v1/sku-stocks/skus/{skuId}/has-stock")
    R<Boolean> getSkuHasStock(@PathVariable Long skuId);

}
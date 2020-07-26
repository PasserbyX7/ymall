package com.yinn.ymall.order.feign;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.order.dto.SpuDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductFeignService {

    @GetMapping("/api/product/v1/spus/skus/{skuId}")
    R<SpuDTO> getSpuBySkuId(@PathVariable Long skuId);

}

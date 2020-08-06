package com.yinn.ymall.product.feign;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.dto.SkuEsDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("search-service")
public interface SearchFeignService {

    @PostMapping("/api/search/v1/products/actions/up")
    R<Void>productUp(@RequestBody List<SkuEsDTO> skuEsDTOs);

    @DeleteMapping("/api/search/v1/products/actions/down")
    R<Void> productDown(@RequestBody Long spuId);

}
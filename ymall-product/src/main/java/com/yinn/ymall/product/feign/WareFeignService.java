package com.yinn.ymall.product.feign;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.dto.SkuHasStockDTO;
import com.yinn.ymall.product.dto.SkuStockDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ware-service")
public interface WareFeignService {

    @PostMapping("/api/ware/v1/sku-stocks/skus/has-stock")
    R<List<SkuHasStockDTO>>listSkuHasStock(@RequestParam List<Long> skuIds);

    @PostMapping("/api/ware/v1/sku-stocks")
    R<Void> saveSkuStock(@RequestBody SkuStockDTO skuStockDTO);
}
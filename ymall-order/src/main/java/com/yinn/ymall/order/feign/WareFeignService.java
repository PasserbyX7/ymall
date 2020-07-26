package com.yinn.ymall.order.feign;

import java.math.BigDecimal;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.order.dto.SkuLockDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ware-service")
public interface WareFeignService {

    @GetMapping("/api/ware/v1/freight/member-addresses/{memberAddressId}")
    R<BigDecimal> getFreight(@PathVariable Long memberAddressId);

    @PostMapping("/api/ware/v1/sku-stocks/actions/lock")
    R<Void> lock(@RequestBody SkuLockDTO skuLockDTO);

}
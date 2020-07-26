package com.yinn.ymall.ware.feign;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.ware.dto.OrderDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("order-service")
public interface OrderFeignService {

    @GetMapping("/api/order/v1/orders/{orderSn}")
    R<OrderDTO>getOrderBySn(String orderSn);

}
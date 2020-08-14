package com.yinn.ymall.seckill.feign;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.seckill.dto.SeckillSessionDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("coupon-service")
public interface CouponFeignService {

    @GetMapping("/api/coupon/v1/seckill-sessions/days/{days}")
    R<List<SeckillSessionDTO>> getByDays(@PathVariable Integer days);

}
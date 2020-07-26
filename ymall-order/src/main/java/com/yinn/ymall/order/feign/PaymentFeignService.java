package com.yinn.ymall.order.feign;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.order.dto.PaymentInfoDTO;
import com.yinn.ymall.order.dto.PrePaymentDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("payment-service")
public interface PaymentFeignService {

    @PostMapping("/api/payment/v1/payments/wx-mini")
    R<PrePaymentDTO> wxMiniPay(@RequestBody PaymentInfoDTO paymentInfoDTO);

}
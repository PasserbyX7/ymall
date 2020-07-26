package com.yinn.ymall.payment.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class OrderPaidDTO {
    private String orderSn;
    private BigDecimal payAmount;
    private String tradeNo;
}
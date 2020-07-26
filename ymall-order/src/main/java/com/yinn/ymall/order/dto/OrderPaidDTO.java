package com.yinn.ymall.order.dto;

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
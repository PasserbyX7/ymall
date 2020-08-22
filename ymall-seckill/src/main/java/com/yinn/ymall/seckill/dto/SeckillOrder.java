package com.yinn.ymall.seckill.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SeckillOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * memberId
     */
    private Long memberId;

    /**
     * orderSn
     */
    private String orderSn;

    /**
     * 秒杀价
     */
    private BigDecimal price;

    /**
     * 秒杀量
     */
    private Integer count;
}
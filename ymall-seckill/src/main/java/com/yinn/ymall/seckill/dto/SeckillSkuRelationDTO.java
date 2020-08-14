package com.yinn.ymall.seckill.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class SeckillSkuRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 活动id
     */
    private Long promotionId;

    /**
     * 秒杀场次id
     */
    private Long sessionId;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 秒杀价
     */
    private BigDecimal price;

    /**
     * 秒杀量
     */
    private Integer count;

    /**
     * 限购量
     */
    private Integer limit;

    /**
     * 排序
     */
    private Integer sort;

}
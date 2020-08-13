package com.yinn.ymall.coupon.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName
@Accessors(chain = true)
public class SeckillSkuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
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

package com.yinn.ymall.coupon.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.coupon.entity.SeckillSession;
import com.yinn.ymall.coupon.entity.SeckillSkuRelation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SeckillSessionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 关联sku实体类集合
     */
    private List<SeckillSkuRelation> skus;

    static public SeckillSessionDTO convertFrom(SeckillSession seckillSession) {
        return new SeckillSessionDTOConverter().doBackward(seckillSession, SeckillSessionDTO.class);
    }

    private static class SeckillSessionDTOConverter implements Convert<SeckillSessionDTO, SeckillSession> {
    }

}
package com.yinn.ymall.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SkuHasStockDTO {
    private Long skuId;
    private Boolean isHasStock;
}
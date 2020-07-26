package com.yinn.ymall.order.dto;

import java.util.List;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.order.entity.OrderItem;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SkuLockDTO {
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 配送电话
     */
    private String phone;
    /**
     * 工单项列表
     */
    List<OrderTaskDTO>orderTaskDTOs;

    @Data
    public static class OrderTaskDTO {
        /**
         * skuId
         */
        private Long skuId;
        /**
         * 商品数
         */
        private Integer count;

        public static OrderTaskDTO convertFor(OrderItem orderItem) {
            return new OrderTaskDTOConverter().doBackward(orderItem, OrderTaskDTO.class);
        }

        private static class OrderTaskDTOConverter implements Convert<OrderTaskDTO, OrderItem>{
        }

    }
}
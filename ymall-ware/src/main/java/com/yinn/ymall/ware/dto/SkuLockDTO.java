package com.yinn.ymall.ware.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.ware.entity.OrderTask;

import lombok.Data;

@Data
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

    public List<OrderTask> convertToOrderTaskList() {
        // @formatter:off
        return orderTaskDTOs
                            .stream()
                            .map(OrderTaskDTO::convertToOrderTask)
                            .map(e->e.setOrderSn(orderSn))
                            .map(e->e.setPhone(phone))
                            .map(e->e.setConsignee(consignee))
                            .collect(Collectors.toList());
        // @formatter:on
    }

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

        public OrderTask convertToOrderTask() {
            return new OrderTaskDTOConverter().doForward(this, OrderTask.class);
        }

        private static class OrderTaskDTOConverter implements Convert<OrderTaskDTO, OrderTask>{
        }

    }
}
package com.yinn.ymall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.order.dto.CartItemDTO;
import com.yinn.ymall.order.entity.OrderItem;

/**
 * 订单所包含sku信息
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
public interface OrderItemService extends IService<OrderItem> {
    OrderItem getOrderItem(CartItemDTO cartItemDTO);
}

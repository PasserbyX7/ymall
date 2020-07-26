package com.yinn.ymall.order.dao;

import com.yinn.ymall.order.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单所包含sku信息
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItem> {
}

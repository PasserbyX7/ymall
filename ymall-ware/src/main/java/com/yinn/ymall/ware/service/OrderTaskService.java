package com.yinn.ymall.ware.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.ware.entity.OrderTask;

/**
 * 库存工单表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-08 01:19:23
 */
public interface OrderTaskService extends IService<OrderTask> {

	List<OrderTask> listByOrderSn(String orderSn);
}


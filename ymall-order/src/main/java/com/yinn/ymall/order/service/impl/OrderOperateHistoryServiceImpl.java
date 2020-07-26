package com.yinn.ymall.order.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.order.dao.OrderOperateHistoryDao;
import com.yinn.ymall.order.entity.OrderOperateHistory;
import com.yinn.ymall.order.service.OrderOperateHistoryService;


@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryDao, OrderOperateHistory> implements OrderOperateHistoryService {
}
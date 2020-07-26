package com.yinn.ymall.ware.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.ware.dao.OrderTaskDao;
import com.yinn.ymall.ware.entity.OrderTask;
import com.yinn.ymall.ware.service.OrderTaskService;

@Service
public class OrderTaskServiceImpl extends ServiceImpl<OrderTaskDao, OrderTask> implements OrderTaskService {

    @Override
    public List<OrderTask> listByOrderSn(String orderSn) {
        return list(Wrappers.<OrderTask>lambdaQuery().eq(OrderTask::getOrderSn,orderSn));
    }
}
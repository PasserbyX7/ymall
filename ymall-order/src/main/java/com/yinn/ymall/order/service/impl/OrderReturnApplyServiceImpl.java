package com.yinn.ymall.order.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.order.dao.OrderReturnApplyDao;
import com.yinn.ymall.order.entity.OrderReturnApply;
import com.yinn.ymall.order.service.OrderReturnApplyService;


@Service
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyDao, OrderReturnApply> implements OrderReturnApplyService {
}
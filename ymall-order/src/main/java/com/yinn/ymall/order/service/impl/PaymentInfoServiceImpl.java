package com.yinn.ymall.order.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.order.dao.PaymentInfoDao;
import com.yinn.ymall.order.entity.PaymentInfo;
import com.yinn.ymall.order.service.PaymentInfoService;


@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoDao, PaymentInfo> implements PaymentInfoService {

    @Override
    public PaymentInfo getByOrderSn(String orderSn) {
        return getOne(Wrappers.<PaymentInfo>lambdaQuery().eq(PaymentInfo::getOrderSn,orderSn));
    }
}
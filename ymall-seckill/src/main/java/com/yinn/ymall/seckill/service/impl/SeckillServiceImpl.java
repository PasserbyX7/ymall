package com.yinn.ymall.seckill.service.impl;

import com.yinn.ymall.seckill.feign.CouponFeignService;
import com.yinn.ymall.seckill.service.SeckillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImpl implements SeckillService{

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired

    @Override
    public void up(int days) {
        //拿到所有商品数据

    }

}
package com.yinn.ymall.ware.service.impl;

import java.math.BigDecimal;

import com.yinn.ymall.ware.service.FreightService;

import org.springframework.stereotype.Service;

@Service
public class FreightServiceImpl implements FreightService {

    @Override
    public BigDecimal getFreight(Long memberAddressId) {
        // TODO 运费计算
        return BigDecimal.valueOf(10.0);
    }

}
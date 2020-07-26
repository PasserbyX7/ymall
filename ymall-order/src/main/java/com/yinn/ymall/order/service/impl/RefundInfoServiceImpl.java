package com.yinn.ymall.order.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.order.dao.RefundInfoDao;
import com.yinn.ymall.order.entity.RefundInfo;
import com.yinn.ymall.order.service.RefundInfoService;


@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfo> implements RefundInfoService {
}
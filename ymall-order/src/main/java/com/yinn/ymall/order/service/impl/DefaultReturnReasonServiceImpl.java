package com.yinn.ymall.order.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.order.dao.DefaultReturnReasonDao;
import com.yinn.ymall.order.entity.DefaultReturnReason;
import com.yinn.ymall.order.service.DefaultReturnReasonService;


@Service
public class DefaultReturnReasonServiceImpl extends ServiceImpl<DefaultReturnReasonDao, DefaultReturnReason> implements DefaultReturnReasonService {
}
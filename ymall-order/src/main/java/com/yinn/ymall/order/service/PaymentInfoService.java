package com.yinn.ymall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.order.entity.PaymentInfo;

/**
 * 支付信息表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
public interface PaymentInfoService extends IService<PaymentInfo> {

	PaymentInfo getByOrderSn(String orderSn);
}


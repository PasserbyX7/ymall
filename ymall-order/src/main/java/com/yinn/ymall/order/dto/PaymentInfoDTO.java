package com.yinn.ymall.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yinn.ymall.common.utils.Convert;
import com.yinn.ymall.order.entity.PaymentInfo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class PaymentInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 用户openId
     */
    private String openId;
    /**
     * 订单名
     */
    private String orderName = "印迹商城微信小程序";
    /**
     * 交易金额
     */
    private BigDecimal payAmount;

    public static PaymentInfoDTO convertFrom(PaymentInfo paymentInfo) {
        return new PaymentInfoDTOConverter().doBackward(paymentInfo, PaymentInfoDTO.class);
    }

    private static class PaymentInfoDTOConverter implements Convert<PaymentInfoDTO, PaymentInfo> {
    }

}
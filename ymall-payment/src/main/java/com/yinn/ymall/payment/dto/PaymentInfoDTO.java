package com.yinn.ymall.payment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.yinn.ymall.common.utils.Convert;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
    private String orderName;
    /**
     * 交易金额
     */
    private BigDecimal payAmount;

    public PayRequest convertToPayRequest() {
        return new PaymentInfoDTOConverter().doForward(this, null);
    }

    private static class PaymentInfoDTOConverter implements Convert<PaymentInfoDTO, PayRequest> {

        @Override
        public PayRequest doForward(PaymentInfoDTO paymentInfoDTO, Class<PayRequest> clazz) {
            var payRequest = new PayRequest();
            payRequest.setOrderName(paymentInfoDTO.getOrderName());
            payRequest.setOrderId(paymentInfoDTO.getOrderSn());
            payRequest.setOrderAmount(paymentInfoDTO.getPayAmount().doubleValue());
            payRequest.setOpenid(paymentInfoDTO.getOpenId());
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MINI);
            return payRequest;
        }
    }
}
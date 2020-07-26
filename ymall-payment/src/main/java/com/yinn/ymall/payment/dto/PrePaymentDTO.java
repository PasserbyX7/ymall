package com.yinn.ymall.payment.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lly835.bestpay.model.PayResponse;
import com.yinn.ymall.common.utils.Convert;

import lombok.Data;

@Data
public class PrePaymentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String timeStamp;
    private String nonceStr;
    @JsonProperty("package")
    private String packAge;
    private String signType;
    private String paySign;

    public static PrePaymentDTO convertFor(PayResponse payResponse) {
        return new PrePaymentDTOConverter().doBackward(payResponse,PrePaymentDTO.class);
    }

    private static class PrePaymentDTOConverter implements Convert<PrePaymentDTO, PayResponse> {
    }
}
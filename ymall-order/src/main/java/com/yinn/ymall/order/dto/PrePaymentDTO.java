package com.yinn.ymall.order.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

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

}
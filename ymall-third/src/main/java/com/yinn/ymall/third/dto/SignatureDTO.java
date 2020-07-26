package com.yinn.ymall.third.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignatureDTO {
    private String accessid;
    private String policy;
    private String signature;
    private String dir;
    private String host;
    private String expire;
}
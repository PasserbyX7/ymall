package com.yinn.ymall.ware.service;

import java.math.BigDecimal;

public interface FreightService {

	BigDecimal getFreight(Long memberAddressId);
}
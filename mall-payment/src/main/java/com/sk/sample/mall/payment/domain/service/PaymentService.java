package com.sk.sample.mall.payment.domain.service;

import com.sk.sample.mall.payment.domain.model.Purchase;

public interface PaymentService {
	Purchase purchase(Purchase purchase);
}

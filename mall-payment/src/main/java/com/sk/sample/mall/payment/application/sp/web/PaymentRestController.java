package com.sk.sample.mall.payment.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sample.mall.payment.domain.model.Purchase;
import com.sk.sample.mall.payment.domain.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentRestController implements PaymentService {
	@Autowired
	private PaymentService paymentService;
	
	@Override
	@PostMapping("/purchases")
	public Purchase purchase(@RequestBody Purchase purchase) {
		return paymentService.purchase(purchase);
	}

}

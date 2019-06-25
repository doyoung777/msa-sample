package com.sk.sample.mall.order.application.proxy.feign;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import com.sk.sample.mall.order.application.proxy.feign.dto.payment.Purchase;

@Service
public class PaymentProxy {
 
	@Autowired
	private PaymentClient paymentClient;
	
	public Purchase purchase(Purchase purchase) {
		return paymentClient.purchase(purchase).getContent();
	}

	@FeignClient(name="payments", url="http://localhost:11005", configuration=FeignClientConfiguration.class)
	interface PaymentClient {
		@PostMapping("payment/purchases")
		Resource<Purchase> purchase(Purchase purchase);
		
	}
}


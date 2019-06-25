package com.sk.sample.mall.order.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.sample.mall.order.application.proxy.feign.AccountProxy;
import com.sk.sample.mall.order.application.proxy.feign.PaymentProxy;
import com.sk.sample.mall.order.application.proxy.feign.ProductProxy;
import com.sk.sample.mall.order.application.proxy.feign.dto.account.Account;
import com.sk.sample.mall.order.application.proxy.feign.dto.payment.Purchase;
import com.sk.sample.mall.order.application.proxy.feign.dto.product.Product;
import com.sk.sample.mall.order.domain.model.CreditCard;
import com.sk.sample.mall.order.domain.model.Order;
import com.sk.sample.mall.order.domain.repository.OrderRepository;

@Service("orderLogic")
public class OrderLogic implements OrderService {

	@Autowired
	private OrderRepository orderReository;
	
	@Autowired
	private AccountProxy accountProxy;
	
	@Autowired
	private ProductProxy productProxy;
	
	@Autowired
	private PaymentProxy paymentProxy;
	
	@Override
	public void purchase(Long orderId) {
		Order order = orderReository.findOne(orderId);
		if(order != null) {
			Account account = null;
			Product product = null;
			Purchase purchase = null;
			
			try {
				account = accountProxy.findAccount(order.getBuyerAccountId());
				System.out.print(account.toString());
				product = productProxy.findProduct(order.getProductId());
				System.out.print(product.toString());	
				
			} catch(Exception e) {
				e.printStackTrace();
				if (account == null) {
					System.err.println("Getting account information is failed.");
					return;
				}
				
				if (product == null) {
					System.err.println("Getting product information is failed.");
					return;
				}
					
				System.err.println("Unkown error.");
				
				
			}
			
			
			// Calculate total price
			order.setTotalPrice(order.getProductCount() * product.getPrice().getValue());
			orderReository.save(order);
			
			// Process payment
			purchase = paymentProxy.purchase(new Purchase(order.getCreditCard(), order.getTotalPrice()));
			
			// Update order
			if (purchase.getSuccessed()) {
				order.setPurchased(true);
				orderReository.save(order);
			}
				
			
		} else {
			System.err.println("error");
			return;
		}
		
		// TODO Auto-generated method stub
		// 구매자. 상품 조회 후 주문 이력 생성
		// orderId로 Order 조회
		// Order의 buyerAccountId로 Account 조회
		// Order의 productId로 Product 조회
		// totalPrice 계산
		// Order 저장
		// 결제 시 mall-payment 서비스의 결제 API 호출  (CreditCard, totalPrice 넘기고 결과 수신)
	}

}

package com.sk.sample.mall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.sk.sample.mall.order.domain.model.CreditCard;
import com.sk.sample.mall.order.domain.model.Order;
import com.sk.sample.mall.order.domain.repository.OrderRepository;
import com.sk.sample.mall.order.domain.service.OrderService;
import com.sk.sample.mall.shared.domain.Address;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {
	
	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(OrderRepository orderRepository) {	
		return (args) -> {
			insertOrders(orderRepository);
			displayOrders(orderRepository);
			
			orderService.purchase(new Long(1));
			displayOrders(orderRepository);
		};
	}
	
	public void insertOrders(OrderRepository orderRepository) {
		Order order1 = new Order(new Long(1), new Long(1), 3);
		order1.setCreditCard(new CreditCard("1000-2000-3000-0001", "01/20"));
		order1.setShippingAddress(new Address(123456, "경기도 성남시 분당구 정자동 25-1 sk u-타워"));
		// purchased : false, totalPrice : null
		orderRepository.save(order1);
		
		Order order2 = new Order(new Long(2), new Long(1), 10);
		order2.setCreditCard(new CreditCard("1000-2000-0001-0001", "02/22"));
		order2.setShippingAddress(new Address(100200, "서울시 강남구 역삼동 100-1"));
		// purchased : false, totalPrice : null
		orderRepository.save(order2);
		
		Order order3 = new Order(new Long(3), new Long(2), 10);
		order3.setCreditCard(new CreditCard("1000-0001-3000-0001", "12/23"));
		order3.setShippingAddress(new Address(101201, "서울시 동작구 상도동 1-1"));
		// purchased : false, totalPrice : null
		orderRepository.save(order3);
		
		Order order4 = new Order(new Long(4), new Long(3), 5);
		order4.setCreditCard(new CreditCard("1000-2110-7700-0001", "05/20"));
		order4.setShippingAddress(new Address(103303, "서울시 종로구 관철동 22-10"));
		// purchased : false, totalPrice : null
		orderRepository.save(order4);
		
	}
	
	public void displayOrders(OrderRepository orderRepository) {
		System.out.println("***************************************************************");
		
		Iterable<Order> orderList = orderRepository.findAll();
		for(Order order : orderList) {
			System.out.println(order.toString());	
		}
		
		System.out.println("***************************************************************");
	}
}

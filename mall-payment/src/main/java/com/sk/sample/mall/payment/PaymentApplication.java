package com.sk.sample.mall.payment;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.sk.sample.mall.payment.domain.model.Credit;
import com.sk.sample.mall.payment.domain.model.CreditCard;
import com.sk.sample.mall.payment.domain.repository.CreditRepository;
import com.sk.sample.mall.payment.domain.repository.PurchaseRepository;
import com.sk.sample.mall.shared.domain.Address;

@SpringBootApplication
@EnableFeignClients
public class PaymentApplication {

	@Autowired
	private CreditRepository creditRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initData(CreditRepository creditRepository) {	
		return (args) -> {
			insertCredits(creditRepository);
			displayCredits(creditRepository);
			
		};
	}
	
	public void insertCredits(CreditRepository creditRepository) {
		Credit credit1 = new Credit(new CreditCard("1000-2000-3000-0001", "01/20"), 1000000);
		creditRepository.save(credit1);
		
		Credit credit2 = new Credit(new CreditCard("1000-2000-0001-0001", "02/22"), 1000000);
		creditRepository.save(credit2);
		
		Credit credit3 = new Credit(new CreditCard("1000-0001-3000-0001", "12/23"), 1000000);
		creditRepository.save(credit3);
		
		Credit credit4 = new Credit(new CreditCard("1000-2110-7700-0001", "05/20"), 1000000);
		creditRepository.save(credit4);
		
	}
	
	public void displayCredits(CreditRepository creditRepository) {
		System.out.println("***************************************************************");
		
		Iterable<Credit> creditList = creditRepository.findAll();
		for(Credit credit : creditList) {
			System.out.println(credit.toString());	
		}
		
		System.out.println("***************************************************************");
	}
}

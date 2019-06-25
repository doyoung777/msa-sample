package com.sk.sample.mall.payment.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.sample.mall.payment.domain.model.Credit;
import com.sk.sample.mall.payment.domain.model.CreditCard;
import com.sk.sample.mall.payment.domain.model.Purchase;
import com.sk.sample.mall.payment.domain.repository.CreditRepository;
import com.sk.sample.mall.payment.domain.repository.PurchaseRepository;

@Service
public class PaymentLogic implements PaymentService {
	@Autowired
	private CreditRepository creditRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	public Purchase purchase(Purchase purchase) {
		purchase.setSuccessed(false);
		purchase.setPurchasedDate(new Date());
		
		CreditCard requestedCreditCard = purchase.getCreditCard();
		Integer amount = purchase.getPurchasedAmount();
		
		Credit credit = creditRepository.findByCreditCardCardNumber(requestedCreditCard.getCardNumber());
		
		if(credit == null) {
			System.err.println("no credit");
			return purchase;
		}
		
		if(!credit.getCreditCard().getValidThru().equals(requestedCreditCard.getValidThru())) {
			System.err.println("not matched validThru");
			return purchase;
		}
		
		/* Valid Thru 유효성 체크 */
		
		if(credit.getUsedAmount() + amount > credit.getLimitAmount()) {
			System.err.println("한도 초과");
			return purchase;
		}
		
		credit.setUsedAmount(credit.getUsedAmount() + amount);
		creditRepository.save(credit);
		
		purchase.setSuccessed(true);
		purchaseRepository.save(purchase);
		
		return purchase;
	}
}

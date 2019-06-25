package com.sk.sample.mall.order.domain.model;

import javax.persistence.Embeddable;

import com.sk.sample.mall.shared.base.ValueObject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
public class CreditCard implements ValueObject {

	private String cardNumber;
	private String validThru;
	
	public CreditCard(String cardNumber, String validThru) {
		this.cardNumber = cardNumber;
		this.validThru = validThru;
	}
}

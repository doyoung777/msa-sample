package com.sk.sample.mall.order.application.proxy.feign.dto.payment;

import java.util.Date;


import javax.persistence.Entity;

import com.sk.sample.mall.order.application.proxy.feign.dto.product.Money;
import com.sk.sample.mall.order.application.proxy.feign.dto.product.Product;
import com.sk.sample.mall.order.application.proxy.feign.dto.product.ProductDescription;
import com.sk.sample.mall.order.domain.model.CreditCard;
import com.sk.sample.mall.shared.base.AbstractEntity;
import com.sk.sample.mall.shared.base.AggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Purchase extends AbstractEntity implements AggregateRoot {
	private CreditCard creditCard;
	private Integer purchasedAmount;
	private Boolean successed;
	private Date purchasedDate;
	
	public Purchase(CreditCard creditCard, Integer purchasedAmount) {
		this.creditCard = creditCard;
		this.purchasedAmount = purchasedAmount;
		this.successed = false;
		this.purchasedDate = new Date();
	}
}

package com.sk.sample.board.domain.model;

import com.sk.sample.board.domain.base.ValueObject;

public class NoticeAccount implements ValueObject {
	private String name;
	
	public NoticeAccount() {
	}
	
	public NoticeAccount(String name) {
		this.name = name;
	}
}

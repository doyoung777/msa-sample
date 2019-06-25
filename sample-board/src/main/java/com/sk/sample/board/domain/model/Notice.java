package com.sk.sample.board.domain.model;

import javax.persistence.Entity;

import com.sk.sample.board.domain.base.AbstractEntity;
import com.sk.sample.board.domain.base.AggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Notice extends AbstractEntity implements AggregateRoot{
	
	private String title;
	private String content;
	private NoticeCategory category;
	private Long writerId;
	private String tag;
	
	public Notice() {
	}
	
	public Notice(String title, String content, NoticeCategory category, Long writerId, String tag) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.writerId = writerId;
		this.tag = tag;
	}
}

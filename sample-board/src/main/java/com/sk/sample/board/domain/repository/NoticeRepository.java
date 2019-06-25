package com.sk.sample.board.domain.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sk.sample.board.domain.model.Notice;

@RepositoryRestResource
public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long>,
										  QueryDslPredicateExecutor<Notice> {

}

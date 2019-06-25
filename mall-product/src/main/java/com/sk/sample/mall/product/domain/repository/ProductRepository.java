package com.sk.sample.mall.product.domain.repository;

import java.util.List;


import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.querydsl.core.types.Predicate;
import com.sk.sample.mall.product.domain.model.Product;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
                                           QueryDslPredicateExecutor<Product> {

	Product findOne(Long id);
	
	List<Product> findAll();
	List<Product> findAll(Predicate predicate);
	
	Product findByName(@Param("name") String name);
	List<Product> findByNameLike(@Param("name") String name);

}
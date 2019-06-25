package com.sk.sample.mall.product.domain.service;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sk.sample.mall.product.domain.model.Product;

public interface ProductService {
	
	Product findById(Long id);
	
	List<Product> findAll();
	Page<Product> findAll(Pageable pageable);
	
	Product findByName(String name);
	List<Product> findByNameLike(String name);
	
	Product register(Product product);
	Product update(Long id, Product product);
	void delete(Long id);
}

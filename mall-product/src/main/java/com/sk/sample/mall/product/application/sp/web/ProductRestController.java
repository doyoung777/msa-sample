package com.sk.sample.mall.product.application.sp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sample.mall.product.domain.model.Product;
import com.sk.sample.mall.product.domain.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductRestController implements ProductService {
	@Autowired
	private ProductService productService;
	
	@Override
	@GetMapping
	public List<Product> findAll() {
		return productService.findAll(); 
	}
	
	@Override
	//@GetMapping
	public Page<Product> findAll(Pageable pageable) {
		return productService.findAll(pageable); 
	}
	
	@Override
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") Long id) {
		return productService.findById(id);
	}

	@Override
	@GetMapping("/search/name")
	public Product findByName(@RequestParam("name") String name) {
		return productService.findByName(name);
	}

	
	@Override
	@GetMapping("/search-like/name")
	public List<Product> findByNameLike(@RequestParam("name") String name) {
		return productService.findByNameLike(name);
	}
	
	
	@Override
	@PostMapping
	public Product register(@RequestBody Product product) {
		return productService.register(product);
	}

	@Override
	@PutMapping("/{id}")
	public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
		return productService.update(id, product);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		productService.delete(id);
	}

}

package com.sk.sample.mall.product.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sk.sample.mall.product.domain.model.Product;
import com.sk.sample.mall.product.domain.model.SizeType;
import com.sk.sample.mall.product.domain.repository.ProductRepository;

@Service("productLogic")
public class ProductLogic implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly=true)
	public Product findById(Long id) {
		return productRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Product> findByNameLike(String name) {
		return productRepository.findByNameLike(name);
	}
	

	@Override
	@Transactional
	public Product register(Product product) {
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public Product update(Long id, Product newProduct) {
		Product oldProduct = productRepository.findOne(id);
		if(oldProduct != null) {
			BeanUtils.copyProperties(newProduct,  oldProduct, "id");
			return productRepository.save(oldProduct);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productRepository.delete(id);
	}
}

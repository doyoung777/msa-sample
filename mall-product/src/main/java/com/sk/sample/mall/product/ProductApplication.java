package com.sk.sample.mall.product;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import com.querydsl.core.types.Predicate;
import com.sk.sample.mall.product.domain.model.ColorType;
import com.sk.sample.mall.product.domain.model.Money;
import com.sk.sample.mall.product.domain.model.Product;
import com.sk.sample.mall.product.domain.model.ProductDescription;
import com.sk.sample.mall.product.domain.model.QProduct;
import com.sk.sample.mall.product.domain.model.SizeType;
import com.sk.sample.mall.product.domain.repository.ProductRepository;

@SpringBootApplication
@EnableHypermediaSupport(type=EnableHypermediaSupport.HypermediaType.HAL)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner execSampleCode(ProductRepository productRepository) {	
		return (args) -> {
			insertProducts(productRepository);
			displayProducts(productRepository);
			
			executeExample2(productRepository);
			executeExample3(productRepository);
			executeExample4(productRepository);
			executeExample5(productRepository);
			executeExample6(productRepository);
			executeExample7(productRepository);
		};
	}
	
	public void insertProducts(ProductRepository productRepository) {
		Product product1 = new Product("Iron Man", new Money(3000), new ProductDescription(ColorType.RED, SizeType.L));
		productRepository.save(product1);
		
		Product product2 = new Product("Captain America", new Money(20000), new ProductDescription(ColorType.BLUE, SizeType.M));
		productRepository.save(product2);
		
		Product product3 = new Product("Winter Soldier", new Money(15000), new ProductDescription(ColorType.WHITE, SizeType.M));
		productRepository.save(product3);
	}
	
	public void displayProducts(ProductRepository productRepository) {
		System.out.println("***************************************************************");
		
		Iterable<Product> productList = productRepository.findAll();
		for(Product product : productList) {
			System.out.println(product.toString());	
		}
		
		System.out.println("***************************************************************");
	}
	
	public void executeExample2(ProductRepository productRepository) {
		Product product = productRepository.findByName("Iron Man");
		product.setPrice(new Money(25000));
		productRepository.save(product);
		
		System.out.println("***************************example2****************************");
		displayProducts(productRepository);
	}
	
	public void executeExample3(ProductRepository productRepository) {
		Predicate predicate = QProduct.product.productDescription.sizeType.eq(SizeType.M);
		List<Product> products = productRepository.findAll(predicate);
		
		System.out.println("***************************example3****************************");
		System.out.println("Result: " + products.toString());
	}
	
	public void executeExample4(ProductRepository productRepository) {
		Predicate predicate = QProduct.product.productDescription.colorType.eq(ColorType.BLUE);
		List<Product> products = productRepository.findAll(predicate);
		
		System.out.println("***************************example4****************************");
		System.out.println("Result: " + products.toString());
	}
	
	public void executeExample5(ProductRepository productRepository) {
		Predicate predicate = QProduct.product.price.value.goe(17000);
		List<Product> products = productRepository.findAll(predicate);
		
		System.out.println("***************************example5****************************");
		System.out.println("Result: " + products.toString());
	}
	
	public void executeExample6(ProductRepository productRepository) {
		Predicate predicate = QProduct.product.price.value.loe(21000);
		List<Product> products = productRepository.findAll(predicate);
		
		System.out.println("***************************example6****************************");
		System.out.println("Result: " + products.toString());
	}
	
	public void executeExample7(ProductRepository productRepository) {
		Predicate predicate = QProduct.product.price.value.gt(10000).and(
							  QProduct.product.price.value.lt(20000)
							  );
		List<Product> products = productRepository.findAll(predicate);
		
		System.out.println("***************************example7****************************");
		System.out.println("Result: " + products.toString());
	}
	
	//http://www.querydsl.com/static/querydsl/4.2.1//apidocs/
}

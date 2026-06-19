package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dao.ProductRepository;
import com.dto.ProductRequestDto;
import com.dto.ProductResponseDto;
import com.model.Product;
import com.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	public final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
		Product product = new Product();
		product.setProductName(productRequestDto.getProductName());
		product.setPrice(productRequestDto.getPrice());
		product.setDiscount(productRequestDto.getDiscount());
		product.setAvailable(true);
		product.setStock(productRequestDto.getStock());
		// product.setRating(0);
		Product savedProduct = productRepository.save(product);
		ProductResponseDto productResponseDto = new ProductResponseDto();
		BeanUtils.copyProperties(savedProduct, productResponseDto);
		return productResponseDto;
	}

	@Override
	public List<ProductResponseDto> saveAllProducts(List<ProductRequestDto> productRequestDto) {
		List<Product> products = buildProductsList(productRequestDto);
		productRepository.saveAll(products);
		List<ProductResponseDto> productResponseDtos = buildProductResponseDto(products);
		return productResponseDtos;
	}

	private List<ProductResponseDto> buildProductResponseDto(List<Product> products) {
		List<ProductResponseDto> productResponseDtos = new ArrayList<ProductResponseDto>();
		for (Product product : products) {
			ProductResponseDto productResponseDto = new ProductResponseDto();
			BeanUtils.copyProperties(product, productResponseDto);
			productResponseDtos.add(productResponseDto);
		}
		return productResponseDtos;
	}
	
	public List<ProductResponseDto> findByNameContaining(String productName) {
		List<Product> products = productRepository.findByProductNameContaining(productName);
		List<ProductResponseDto> productResponseDto = buildProductResponseDto(products);
		return productResponseDto;
	}

	private List<Product> buildProductsList(List<ProductRequestDto> productRequestDto) {
		List<Product> products = new ArrayList<Product>();
		for (ProductRequestDto eachProductRequestDto : productRequestDto) {
			Product product = new Product();
			BeanUtils.copyProperties(eachProductRequestDto, product);
			product.setAvailable(true);
			products.add(product);
		}
		return products;
	}

	@Override
	public ProductResponseDto findByName(String name) {
		  Product byProductName = productRepository.findByProductName(name);
		  ProductResponseDto productResponseDto = new ProductResponseDto();
		  BeanUtils.copyProperties(byProductName, productResponseDto);
		return productResponseDto;
	}

	@Override
	public ProductResponseDto updateById(long id,double rating) {
		 Product product = productRepository.findById(id).get();
		 product.setRating(rating);
		 productRepository.save(product);
		 ProductResponseDto productResponseDto = new ProductResponseDto();
		 BeanUtils.copyProperties(product, productResponseDto);
		return productResponseDto;
	}

	@Override
	public String delete(long id) {
		 Product product = productRepository.findById(id).get();
		 String productName = product.getProductName();
		productRepository.deleteById(id);
		return productName;
	}

	@Override
	public List<ProductResponseDto> findAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return buildProductResponseDto(allProducts);
	}



}

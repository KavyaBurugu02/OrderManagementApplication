package com.service;

import java.util.List;

import com.dto.ProductRequestDto;
import com.dto.ProductResponseDto;

public interface ProductService {
	
	public ProductResponseDto saveProduct(ProductRequestDto productRequestDto);
	
	public List<ProductResponseDto> saveAllProducts(List<ProductRequestDto> productRequestDto);
	
	public ProductResponseDto findByName(String name);

	public List<ProductResponseDto> findByNameContaining(String productName);

	public ProductResponseDto updateById(long id,double rating);
	
	public String delete(long id);

	public List<ProductResponseDto> findAllProducts();

}

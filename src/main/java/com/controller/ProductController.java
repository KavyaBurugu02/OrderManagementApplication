package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ProductRequestDto;
import com.dto.ProductResponseDto;
import com.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
//	@Autowired
//	ProductService productService;
   public final ProductService productService;
   
   public ProductController(ProductService productService) {
	   this.productService = productService;
   }
	
	
	@PostMapping("/save")
	public ProductResponseDto save(@RequestBody  ProductRequestDto productRequestDto) {
		return productService.saveProduct(productRequestDto);
	}
	
	@PostMapping("/save-all")
	public List<ProductResponseDto> saveAllProducts(@RequestBody List<ProductRequestDto> productRequestDto){
		return productService.saveAllProducts(productRequestDto);
	}
	
	@GetMapping("/findByName/{name}")
	public ProductResponseDto findByName(@PathVariable(name="name")String productName) {
		return productService.findByName(productName);
	}
	
	@GetMapping("/findBy/{name}")
	public List<ProductResponseDto> findByNameContaining(@PathVariable (name="name")String productName){
	 return	productService.findByNameContaining(productName);	
	}
	
	@PutMapping("/update/{id}")
	public ProductResponseDto updateRatingById(@PathVariable(name="id") long id, @RequestParam (name="rating") double rating) {
		return productService.updateById(id,rating);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public String delete(@PathVariable(name="id")long id) {
		return productService.delete(id);
	}
	
	@GetMapping
	public List<ProductResponseDto> findAllProducts(){
		return productService.findAllProducts();
	}

}

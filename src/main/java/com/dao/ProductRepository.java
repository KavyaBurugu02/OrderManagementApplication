package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.ProductResponseDto;
import com.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
	Product findByProductName(String name);
	
	List<Product> findByProductNameContaining(String name);
	
//	@Query("update Product p set p.rating=:rating where p.productId=:id")
//	Product updateById(@Param("id") long id,@Param("rating") double rating);

}

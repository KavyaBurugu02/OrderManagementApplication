package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
	
	private long productId;
	
	private String productName;
	
    private double price;
		
	private double rating;
	private double discount;
	
     private int stock;
	
	private boolean isAvailable;
	   

}

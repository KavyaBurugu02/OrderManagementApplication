package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products_details")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable=false)
	private double price;
	
	private double rating;
	
	
	private double discount;
	
	@Column(nullable=false)
	private int stock;
	
	private boolean isAvailable;

	public Product(String productName, double price, double rating, double discount, int stock, boolean isAvailable) {
		super();
		this.productName = productName;
		this.price = price;
		this.rating = rating;
		this.discount = discount;
		this.stock = stock;
		this.isAvailable = isAvailable;
	}
	
	

}

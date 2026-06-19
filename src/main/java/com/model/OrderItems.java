package com.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderItems {
	
	private long orderItemId;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public OrderItems(int quantity, Order order, Product product) {
		super();
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

}

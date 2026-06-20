package com.service;

import java.util.List;

import com.dto.OrderRequestDto;
import com.dto.OrderResponseDto;
import com.model.Order;

public interface OrderService {
	
	public OrderResponseDto placeOrder(List<OrderRequestDto> orderRequestDtos);
	

}

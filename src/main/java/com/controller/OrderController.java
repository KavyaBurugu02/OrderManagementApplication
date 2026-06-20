package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.OrderRequestDto;
import com.dto.OrderResponseDto;
import com.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/buy")
	public OrderResponseDto placeOrder(@RequestBody List<OrderRequestDto> orderRequestDtos) {
		return orderService.placeOrder(orderRequestDtos);
		
		
	}

}

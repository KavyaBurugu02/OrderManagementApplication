package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderRepository;
import com.dao.ProductRepository;
import com.dto.OrderItemResponseDto;
import com.dto.OrderRequestDto;
import com.dto.OrderResponseDto;
import com.model.Order;
import com.model.OrderItems;
import com.model.Product;
import com.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public OrderResponseDto placeOrder(List<OrderRequestDto> orderRequestDtos) {
		Order order = new Order();
		order.setStatus("ordered");
		List<OrderItems> orderItemsList = new ArrayList<OrderItems>();	
		for(OrderRequestDto orderRequestDto : orderRequestDtos) {
			OrderItems orderItem = new OrderItems();	
			Product product = productRepository.findById(orderRequestDto.getProductId()).get();
			if(product.getStock()>= orderRequestDto.getQuantity()) {
			orderItem.setQuantity(orderRequestDto.getQuantity());
			orderItem.setProduct(product);
			orderItem.setOrder(order);
			orderItemsList.add(orderItem);	
			productRepository.updateStock(product.getProductId(), product.getStock()-orderRequestDto.getQuantity());
			} 
			
		}
		order.setOrderItems(orderItemsList);
		Order savedOrder = orderRepository.save(order);
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setOrderId(savedOrder.getOrderId());
		orderResponseDto.setStatus(savedOrder.getStatus());
		List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<OrderItemResponseDto>();
		double totalAmount = 0;
		OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
		for(OrderItems orderItems : order.getOrderItems()) {
			orderItemResponseDto.setProductId(orderItems.getProduct().getProductId());
			orderItemResponseDto.setProductName(orderItems.getProduct().getProductName());
			orderItemResponseDto.setQuantity(orderItems.getQuantity());
	double eachProductPrices =		orderItems.getProduct().getPrice()* orderItems.getProduct().getDiscount()/100;
			orderItemResponseDto.setEachProductPrice(eachProductPrices);
			double totalPriceOfEachItem = orderItems.getQuantity() * eachProductPrices;
			orderItemResponseDto.setTotalProductPrice(totalPriceOfEachItem);
			totalAmount += totalPriceOfEachItem;		
		}
		orderResponseDto.setTotalAmount(totalAmount);
		orderItemResponseDtoList.add(orderItemResponseDto);
		orderResponseDto.setOrderItems(orderItemResponseDtoList);	
		return orderResponseDto;
		
	}
	
	

}

package com.joshofreeness.ordertracking.service;

import java.util.List;

import com.joshofreeness.ordertracking.domain.Order;

public interface OrderService {
	
	public List<Order> findAll();

	public void delete(Order order);


	public Order findById(Long id);
	
	public Order save(Order order);


}

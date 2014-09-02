package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import com.joshofreeness.ordertracking.domain.Order;

public interface OrderDao {
	
	public List<Order> findAll();

	public void delete(Order order);

	public List<Order> findAllWithDetail();

	public Order findById(Long id);
	
	public Order save(Order order);

}

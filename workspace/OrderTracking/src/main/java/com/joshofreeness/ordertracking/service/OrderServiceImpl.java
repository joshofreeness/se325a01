package com.joshofreeness.ordertracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshofreeness.ordertracking.domain.Order;
import com.joshofreeness.ordertracking.persistence.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;
	
	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public void delete(Order order) {
		orderDao.delete(order);
		
	}

	@Override
	public Order findById(Long id) {
		return orderDao.findById(id);
	}

	@Override
	public Order save(Order order) {
		return orderDao.save(order);
	}

}

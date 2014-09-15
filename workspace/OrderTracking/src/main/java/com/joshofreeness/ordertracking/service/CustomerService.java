package com.joshofreeness.ordertracking.service;

import java.util.List;

import com.joshofreeness.ordertracking.domain.Customer;

public interface CustomerService {
	
	public List<Customer> findAll();

	public void delete(Customer customer);

	public Customer findById(Long id);
	
	public Customer save(Customer customer);

}

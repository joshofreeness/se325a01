package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import com.joshofreeness.ordertracking.domain.Customer;

public interface CustomerDao {

		public List<Customer> findAll();

		public void delete(Customer customer);

		public Customer findById(Long id);
		
		public Customer save(Customer customer);

}

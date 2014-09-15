package com.joshofreeness.ordertracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.persistence.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;
	
	public List<Customer> findAll(){
		return customerDao.findAll();
	}

	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	public Customer findById(Long id){
		return customerDao.findById(id);
	}
	
	public Customer save(Customer customer){
		return customerDao.save(customer);
	}

}

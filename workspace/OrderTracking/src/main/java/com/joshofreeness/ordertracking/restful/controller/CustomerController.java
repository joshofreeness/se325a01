package com.joshofreeness.ordertracking.restful.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Customers;
import com.joshofreeness.ordertracking.persistence.CustomerDaoImpl;

@Controller
@RequestMapping(value = "/customers")
public class CustomerController {
	
	final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerDaoImpl customerDao; 

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Customers listData(WebRequest webRequest) {
		return new Customers(customerDao.findAll());
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Customer findCustomerById(@PathVariable Long id) {		
		return customerDao.findById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Customer create(@RequestBody Customer Customer, HttpServletResponse response) {
		log.info("Creating Customer: " + Customer);
		customerDao.save(Customer);
		log.info("Customer created successfully with info: " + Customer);
		response.setHeader("Location",  "/Customers/" + Customer.getId());
		return Customer;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Customer Customer, @PathVariable Long id) {
		log.info("Updating Customer: " + Customer);
		customerDao.save(Customer);
		log.info("Customer updated successfully with info: " + Customer);
		//return Customer;
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		log.info("Deleting Customer with id: " + id);
		Customer Customer = customerDao.findById(id);
		customerDao.delete(Customer);
		log.info("Customer deleted successfully");
	}	

}

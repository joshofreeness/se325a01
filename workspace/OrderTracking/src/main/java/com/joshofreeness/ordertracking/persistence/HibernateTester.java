package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.joshofreeness.ordertracking.persistence.CustomerDao;
import com.joshofreeness.ordertracking.domain.Customer;



public class HibernateTester {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		CustomerDao customerDao = ctx.getBean("customerDao", CustomerDao.class);

		// List customers without details
		List<Customer> customers = customerDao.findAll();
		//listCustomers(customers);	
		
		// List customers with details
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);

		
		Customer customer;
		
		// Find customer by ID
		customer = customerDao.findById(1l);
		System.out.println("");
		System.out.println("Customer with id 1:" + customer);
		System.out.println("");		
		
		// Add new customer
		customer = new Customer();
		customer.setFirstName("Michael");
		customer.setLastName("Jackson");
		customer.setCredit(200);
		customerDao.save(customer);
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);

		// Update customer
		customer = customerDao.findById(1l);
		customer.setFirstName("Kim Fung");

		customerDao.save(customer);
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);
		
		// Delete customer
		customer = customerDao.findById(1l);
		customerDao.delete(customer);
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);	
		
	}
	
	private static void listCustomers(List<Customer> customers) {
		System.out.println("");
		System.out.println("Listing customers without details:");
		for (Customer customer: customers) {
			System.out.println(customer);		
			System.out.println();	
		}
	}	
	
	private static void listCustomersWithDetail(List<Customer> customers) {
		System.out.println("");
		System.out.println("Listing customers with details:");
		for (Customer customer: customers) {
			System.out.println(customer);
		
			System.out.println();
		}		
	}	
	
}

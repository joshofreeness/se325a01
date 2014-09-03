package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.joshofreeness.ordertracking.persistence.CustomerDao;
import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Product;



public class HibernateTester {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		CustomerDao customerDao = ctx.getBean("customerDao", CustomerDao.class);
		ProductDao productDao = ctx.getBean("productDao", ProductDao.class);

		// List customers without details
		List<Customer> customers = customerDao.findAll();
		List<Product> products = productDao.findAll();
		//listCustomers(customers);	
		
		// List customers with details
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);
		
		products = productDao.findAllWithDetail();
		listProductsWithDetail(products);

		
		Customer customer;
		Product product;
		
		// Find customer by ID
		customer = customerDao.findById(1l);
		System.out.println("");
		System.out.println("Customer with id 1:" + customer);
		System.out.println("");	
		
		product = productDao.findById(1l);
		System.out.println("");
		System.out.println("Customer with id 1:" + product);
		System.out.println("");		
		
		// Add new customer
		customer = new Customer();
		customer.setFirstName("Michael");
		customer.setLastName("Jackson");
		customer.setAddress("PlaceNear");
		customer.setEmail("ME@place.com");
		customer.setMobPhone(95);
		customer.setSpecialInstructions("GO TO THAT PLACE");
		customer.setCredit(200);
		customerDao.save(customer);
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);
		
		product = new Product();
		product.setCost(13);
		product.setName("Thing");
		productDao.save(product);
		products = productDao.findAllWithDetail();
		listProductsWithDetail(products);

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
	
	private static void listProductsWithDetail(List<Product> customers) {
		System.out.println("");
		System.out.println("Listing products with details:");
		for (Product customer: customers) {
			System.out.println(customer);
			System.out.println();
		}		
	}
}

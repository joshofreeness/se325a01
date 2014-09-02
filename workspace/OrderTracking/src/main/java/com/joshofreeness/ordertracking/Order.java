package com.joshofreeness.ordertracking;

import org.apache.log4j.Logger;

public class Order {
	
	
	private Customer fCustomer;
	private Product fProduct;
	final Logger log;
	
	public Order(Customer customer, Product product){
		log = Logger.getLogger(Product.class);
		fCustomer = customer;
		fProduct = product;
		log.info("New Order Created");
	}

}

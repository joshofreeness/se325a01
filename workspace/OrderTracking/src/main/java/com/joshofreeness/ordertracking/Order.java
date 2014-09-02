package com.joshofreeness.ordertracking;

public class Order {
	
	private Customer fCustomer;
	private Product fProduct;
	
	public Order(Customer customer, Product product){
		fCustomer = customer;
		fProduct = product;
	}

}

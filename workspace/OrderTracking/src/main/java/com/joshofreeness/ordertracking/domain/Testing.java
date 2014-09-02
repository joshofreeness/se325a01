package com.joshofreeness.ordertracking.domain;

public class Testing {

	public static void main(String[] args) {
		Product product = new Product("Eggs", 5);
		Customer cust = new Customer();
		Order order = new Order(cust, product);

	}

}

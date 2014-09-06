package com.joshofreeness.ordertracking.domain;

import java.io.Serializable;
import java.util.List;

public class Customers implements Serializable {


	private static final long serialVersionUID = 2626336752689719901L;
	private List<Customer> customers;

	public Customers() {
	}
	
	public Customers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	 
}

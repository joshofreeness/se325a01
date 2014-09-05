package com.joshofreeness.ordertracking.domain;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {


	private static final long serialVersionUID = 2626336752689719901L;
	private List<Product> products;

	public Products() {
	}
	
	public Products(List<Product> contacts) {
		this.products = contacts;
	}
	
	public List<Product> getContacts() {
		return products;
	}

	public void setContacts(List<Product> contacts) {
		this.products = contacts;
	}
	 
}

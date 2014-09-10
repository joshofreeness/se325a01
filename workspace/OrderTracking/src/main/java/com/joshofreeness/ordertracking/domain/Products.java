package com.joshofreeness.ordertracking.domain;

import java.io.Serializable;
import java.util.List;

public class Products implements Serializable {


	private static final long serialVersionUID = 2626336752689719901L;
	private List<Product> products;

	public Products() {
	}
	
	public Products(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	 
}

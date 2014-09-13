package com.joshofreeness.ordertracking.restful.controller;

import java.io.Serializable;

public class IdParserForNewOrder implements Serializable{
	
	private long id;
	private int customer;
	private int product;
	
	public int getCustomer() {
		return customer;
	}
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
}

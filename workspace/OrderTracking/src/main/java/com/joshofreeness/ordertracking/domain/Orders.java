package com.joshofreeness.ordertracking.domain;

import java.io.Serializable;
import java.util.List;

public class Orders implements Serializable {


	private static final long serialVersionUID = 2626336752689719901L;
	private List<Order> orders;

	public Orders() {
	}
	
	public Orders(List<Order> contacts) {
		this.orders = contacts;
	}
	
	public List<Order> getContacts() {
		return orders;
	}

	public void setContacts(List<Order> contacts) {
		this.orders = contacts;
	}
	 
}

package com.joshofreeness.ordertracking.domain;

import java.io.Serializable;
import java.util.List;

public class Customers implements Serializable {


	private static final long serialVersionUID = 2626336752689719901L;
	private List<Customer> contacts;

	public Customers() {
	}
	
	public Customers(List<Customer> contacts) {
		this.contacts = contacts;
	}
	
	public List<Customer> getContacts() {
		return contacts;
	}

	public void setContacts(List<Customer> contacts) {
		this.contacts = contacts;
	}
	 
}

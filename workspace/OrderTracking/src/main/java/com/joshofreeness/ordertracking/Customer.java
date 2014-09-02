package com.joshofreeness.ordertracking;

import org.apache.log4j.Logger;

public class Customer {

	private int id;
	private String firstName;
	private String address;
	private int mobPhone;
	private String email;
	private String specialInstructions;
	private double credit;
	private final Logger log;
	
	public Customer(){
		log = Logger.getLogger(Product.class);
		log.info("New Customer Created");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMobPhone() {
		return mobPhone;
	}

	public void setMobPhone(int mobPhone) {
		this.mobPhone = mobPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eMail) {
		this.email = eMail;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

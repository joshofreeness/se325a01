package com.joshofreeness.ordertracking.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable{

	private static final long serialVersionUID = 7722022490094349455L;
	private int id;
	private String firstName;
	private String lastName;
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "FIRSTNAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "MOBPHONE")
	public int getMobPhone() {
		return mobPhone;
	}

	public void setMobPhone(int mobPhone) {
		this.mobPhone = mobPhone;
	}
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String eMail) {
		this.email = eMail;
	}
	@Column(name = "SPECIALINSTRUCTIONS")
	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	@Column(name="CREDIT")
	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public int getId() {
		return id;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}

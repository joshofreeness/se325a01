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
	private long id;
	private String firstName;
	private String lastName;
	private String address;
	private int mobPhone;
	private String email;
	private String specialInstructions;
	private double credit;
	private final Logger log = Logger.getLogger(Product.class);
	
	public Customer(){}
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public String toString(){
		return firstName + " " + lastName + " " + address;
	}
	
	public void setId(long id) {
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

	@Column(name="LASTNAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}

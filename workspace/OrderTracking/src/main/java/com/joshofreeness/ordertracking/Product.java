package com.joshofreeness.ordertracking;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id;
	private float fCost;
	private String name;
	private final Logger log = Logger.getLogger(Product.class);
	
	public Product(String input_name, float cost){
		fCost = cost;
		name = input_name;
		log.info("New Product Created");
	}
	
	@Column(name = "COST")
	public float getCost() {
		return fCost;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String input_name) {
		name = input_name;
	}

	public int getId() {
		return id;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public void setId(int id) {
		this.id = id;
	}
}

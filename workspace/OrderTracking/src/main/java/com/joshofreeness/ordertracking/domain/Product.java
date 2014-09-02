package com.joshofreeness.ordertracking.domain;

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

	
	
	private static final long serialVersionUID = -6153234844024969938L;
	private Long id;
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

	public Long getId() {
		return id;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public void setId(Long id) {
		this.id = id;
	}
}

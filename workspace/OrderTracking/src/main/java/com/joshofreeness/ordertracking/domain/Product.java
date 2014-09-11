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
	private float cost;
	private String name;
	// TODO: Possibly add description
	private final Logger log = Logger.getLogger(Product.class);
	
	public Product(){}
	
	public Product(String input_name, float cost){
		this.cost = cost;
		name = input_name;
		log.info("New Product Created");
	}
	
	public String toString(){
		return id + " " + name + " " + cost;
	}
	
	@Column(name = "COST")
	public float getCost() {
		return cost;
	}
	
	public void setCost(float cost){
		this.cost = cost;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String input_name) {
		name = input_name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
}

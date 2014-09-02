package com.joshofreeness.ordertracking;

import org.apache.log4j.Logger;

public class Product {

	private float fCost;
	private String fName;
	private final Logger log;
	
	public Product(String name, float cost){
		log = Logger.getLogger(Product.class);
		fCost = cost;
		fName = name;
		log.info("New Product Created");
	}
	
	public float getCost() {
		return fCost;
	}

	public String getName() {
		return fName;
	}
}

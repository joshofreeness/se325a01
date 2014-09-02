package com.joshofreeness.ordertracking;

public class Product {

	private float fCost;
	private String fName;
	
	public Product(String name, float cost){
		fCost = cost;
		fName = name;
	}
	
	public float getCost() {
		return fCost;
	}

	public String getName() {
		return fName;
	}
}

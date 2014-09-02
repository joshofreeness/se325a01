package com.joshofreeness.ordertracking;

public class Order {
	
	private float fCost;
	private String fName;
	
	public Order(String name, float cost){
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

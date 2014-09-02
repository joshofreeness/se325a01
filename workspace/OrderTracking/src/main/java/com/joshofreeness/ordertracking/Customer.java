package com.joshofreeness.ordertracking;

import org.apache.log4j.Logger;

public class Customer {

	private String fName;
	private String fAddress;
	private int fMobPhone;
	private String eMail;
	private String fSpecialInstructions;
	private final Logger log;
	
	public Customer(){
		log = Logger.getLogger(Product.class);
		log.info("New Customer Created");
	}
	
}

package com.joshofreeness.ordertracking.restful;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Customers;

public class ServerClientMain {
	
	private static final String URL_GET_ALL_CUSTOMERS = "http://localhost:8080/OrderTracking/customers";
	private static final String URL_GET_CUSTOMER_BY_ID = "http://localhost:8080/OrderTracking/customers/{id}";
	private static final String URL_CREATE_CUSTOMER = "http://localhost:8080/OrderTracking/customers/";
	private static final String URL_UPDATE_CUSTOMER = "http://localhost:8080/OrderTracking/customers/{id}";
	private static final String URL_DELETE_CUSTOMER = "http://localhost:8080/OrderTracking/customers/{id}";	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		Customers customers;
		Customer customer;
		
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
		
		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
		listCustomers(customers); 
		
		// Test retrieve contact by id
		System.out.println("Testing retrieve a contact by id :");
		customer = restTemplate.getForObject(URL_GET_CUSTOMER_BY_ID, Customer.class, 1);
		System.out.println(customer);
		System.out.println("");
		
		// Test update contact
		customer = restTemplate.getForObject(URL_UPDATE_CUSTOMER, Customer.class, 1);
		customer.setFirstName("Kim Fung");
		System.out.println("Testing update contact by id :");
		restTemplate.put(URL_UPDATE_CUSTOMER, customer, 1);
		System.out.println("Contact update successfully: " + customer);
		System.out.println("");	
		
		// Testing delete contact
		restTemplate.delete(URL_DELETE_CUSTOMER, 1);
		System.out.println("Testing delete contact by id :");
        customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
        listCustomers(customers);
        
		// Testing create contact
        System.out.println("Testing create contact :");
        Customer newcustomer = new Customer();
        newcustomer.setFirstName("Michael");
        newcustomer.setLastName("Jackson");
        newcustomer.setAddress("PlaceNear");
        newcustomer.setEmail("ME@place.com");
        newcustomer.setMobPhone(95);
        newcustomer.setSpecialInstructions("GO TO THAT PLACE");
        newcustomer.setCredit(200);
        newcustomer = restTemplate.postForObject(URL_CREATE_CUSTOMER, newcustomer, Customer.class);
		System.out.println("Contact created successfully: " + newcustomer);    
	}

	
	private static void listCustomers(Customers contacts) {
		for (Customer contact: contacts.getContacts()) {
			System.out.println(contact);
		}	
		System.out.println("");
	}
}

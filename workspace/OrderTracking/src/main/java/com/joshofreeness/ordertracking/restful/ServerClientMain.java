package com.joshofreeness.ordertracking.restful;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Customers;
import com.joshofreeness.ordertracking.domain.Product;
import com.joshofreeness.ordertracking.domain.Products;

public class ServerClientMain {
	
	private static final String URL_GET_ALL_CUSTOMERS = "http://localhost:8080/ordertracking/customers";
	private static final String URL_GET_CUSTOMER_BY_ID = "http://localhost:8080/ordertracking/customers/{id}";
	private static final String URL_CREATE_CUSTOMER = "http://localhost:8080/ordertracking/customers/";
	private static final String URL_UPDATE_CUSTOMER = "http://localhost:8080/ordertracking/customers/{id}";
	private static final String URL_DELETE_CUSTOMER = "http://localhost:8080/ordertracking/customers/{id}";	
	
	private static final String URL_GET_ALL_PRODUCTS = "http://localhost:8080/ordertracking/products";
	private static final String URL_GET_PRODUCT_BY_ID = "http://localhost:8080/ordertracking/products/{id}";
	private static final String URL_CREATE_PRODUCT = "http://localhost:8080/ordertracking/products/";
	private static final String URL_UPDATE_PRODUCT = "http://localhost:8080/ordertracking/products/{id}";
	private static final String URL_DELETE_PRODUCT = "http://localhost:8080/ordertracking/products/{id}";	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		Customers customers;
		Customer customer;
		Products products;
		Product product;
		
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
		
		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
		listCustomers(customers); 
		
		System.out.println("Testing retrieve all products:");
		products = restTemplate.getForObject(URL_GET_ALL_PRODUCTS, Products.class);
		listProducts(products); 
		
		// Test retrieve contact by id
		System.out.println("Testing retrieve a contact by id :");
		customer = restTemplate.getForObject(URL_GET_CUSTOMER_BY_ID, Customer.class, 1);
		System.out.println(customer);
		System.out.println("");
		
		System.out.println("Testing retrieve a product by id :");
		product = restTemplate.getForObject(URL_GET_PRODUCT_BY_ID, Product.class, 1);
		System.out.println(product);
		System.out.println("");
		
		// Test update contact
		customer = restTemplate.getForObject(URL_UPDATE_CUSTOMER, Customer.class, 1);
		customer.setFirstName("Kim Fung");
		System.out.println("Testing update contact by id :");
		restTemplate.put(URL_UPDATE_CUSTOMER, customer, 1);
		System.out.println("Contact update successfully: " + customer);
		System.out.println("");	
		
		product = restTemplate.getForObject(URL_UPDATE_PRODUCT, Product.class, 1);
		product.setName("Changed Product");
		System.out.println("Testing update contact by id :");
		restTemplate.put(URL_UPDATE_PRODUCT, product, 1);
		System.out.println("Contact update successfully: " + product);
		System.out.println("");	
		
		// Testing delete contact
		restTemplate.delete(URL_DELETE_CUSTOMER, 1);
		System.out.println("Testing delete contact by id :");
        customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
        listCustomers(customers);
        
        restTemplate.delete(URL_DELETE_PRODUCT, 1);
		System.out.println("Testing delete product by id :");
        products = restTemplate.getForObject(URL_GET_ALL_PRODUCTS, Products.class);
        listProducts(products);
        
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
		System.out.println("");	
		
		System.out.println("Testing create product :");
        Product newproduct = new Product();
        newproduct.setName("New Product");
        newproduct.setCost(55);
        newproduct = restTemplate.postForObject(URL_CREATE_PRODUCT, newproduct, Product.class);
		System.out.println("Product created successfully: " + newproduct);    
		System.out.println("");	
		
		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
		listCustomers(customers); 
		
		System.out.println("Testing retrieve all products:");
		products = restTemplate.getForObject(URL_GET_ALL_PRODUCTS, Products.class);
		listProducts(products); 
	}

	
	private static void listCustomers(Customers contacts) {
		for (Customer contact: contacts.getCustomers()) {
			System.out.println(contact);
		}	
		System.out.println("");
	}
	
	private static void listProducts(Products contacts) {
		for (Product contact: contacts.getProducts()) {
			System.out.println(contact);
		}	
		System.out.println("");
	}
}

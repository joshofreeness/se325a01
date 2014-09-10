package com.joshofreeness.ordertracking.restful;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Customers;
import com.joshofreeness.ordertracking.domain.Order;
import com.joshofreeness.ordertracking.domain.Orders;
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
	
	private static final String URL_GET_ALL_ORDERS = "http://localhost:8080/ordertracking/orders";
	private static final String URL_GET_ORDER_BY_ID = "http://localhost:8080/ordertracking/orders/{id}";
	private static final String URL_CREATE_ORDER = "http://localhost:8080/ordertracking/orders/";
	private static final String URL_UPDATE_ORDER = "http://localhost:8080/ordertracking/orders/{id}";
	private static final String URL_DELETE_ORDER = "http://localhost:8080/ordertracking/orders/{id}";	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		Customers customers;
		Customer customer;
		Products products;
		Product product;
		Orders orders;
		Order order;
		
		RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
		
		order = new Order();
		order.setCustomer(restTemplate.getForObject(URL_GET_CUSTOMER_BY_ID, Customer.class, 1));
		order.setProduct(restTemplate.getForObject(URL_GET_PRODUCT_BY_ID, Product.class, 1));
		Order order1 = new Order();
		order1.setCustomer(restTemplate.getForObject(URL_GET_CUSTOMER_BY_ID, Customer.class, 2));
		order1.setProduct(restTemplate.getForObject(URL_GET_PRODUCT_BY_ID, Product.class, 2));
		System.out.println("Testing creating order:");
		order = restTemplate.postForObject(URL_CREATE_ORDER, order, Order.class);
		order = restTemplate.postForObject(URL_CREATE_ORDER, order1, Order.class);
		
		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
		listCustomers(customers); 
		
		System.out.println("Testing retrieve all products:");
		products = restTemplate.getForObject(URL_GET_ALL_PRODUCTS, Products.class);
		listProducts(products); 
		
		System.out.println("Testing retrieve all orders:");
		orders = restTemplate.getForObject(URL_GET_ALL_ORDERS, Orders.class);
		listOrders(orders); 
		
		// Test retrieve contact by id
		System.out.println("Testing retrieve a contact by id :");
		customer = restTemplate.getForObject(URL_GET_CUSTOMER_BY_ID, Customer.class, 1);
		System.out.println(customer);
		System.out.println("");
		
		System.out.println("Testing retrieve a product by id :");
		product = restTemplate.getForObject(URL_GET_PRODUCT_BY_ID, Product.class, 1);
		System.out.println(product);
		System.out.println("");
		
		System.out.println("Testing retrieve a order by id :");
		order = restTemplate.getForObject(URL_GET_ORDER_BY_ID, Order.class, 1);
		System.out.println(order);
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
		
		order = restTemplate.getForObject(URL_UPDATE_ORDER, Order.class, 1);
		order.setCustomer(customer);
		System.out.println("Testing update order by id :");
		restTemplate.put(URL_UPDATE_ORDER, order, 1);
		System.out.println("Contact update successfully: " + order);
		System.out.println("");	
		
		System.out.println("Testing retrieve all orders:");
		orders = restTemplate.getForObject(URL_GET_ALL_ORDERS, Orders.class);
		listOrders(orders); 
		
		// Testing delete contact
		restTemplate.delete(URL_DELETE_ORDER, 1);
		System.out.println("Testing delete order by id :");
        orders = restTemplate.getForObject(URL_GET_ALL_ORDERS, Orders.class);
        listOrders(orders);
        
		restTemplate.delete(URL_DELETE_CUSTOMER, 3);
		System.out.println("Testing delete customer by id :");
        customers = restTemplate.getForObject(URL_GET_ALL_CUSTOMERS, Customers.class);
        listCustomers(customers);
        
        restTemplate.delete(URL_DELETE_PRODUCT, 3);
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
		
		System.out.println("Testing retrieve all orders:");
		orders = restTemplate.getForObject(URL_GET_ALL_ORDERS, Orders.class);
		listOrders(orders); 
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
	
	private static void listOrders(Orders contacts) {
		for (Order contact: contacts.getOrders()) {
			System.out.println(contact);
		}	
		System.out.println("");
	}
}

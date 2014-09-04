package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.joshofreeness.ordertracking.persistence.CustomerDao;
import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Order;
import com.joshofreeness.ordertracking.domain.Product;



public class HibernateTester {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		CustomerDao customerDao = ctx.getBean("customerDao", CustomerDao.class);
		ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
		OrderDao orderDao = ctx.getBean("orderDao",OrderDao.class);

		// List customers without details
		List<Customer> customers = customerDao.findAll();
		List<Product> products = productDao.findAll();
		List<Order> orders = orderDao.findAll();
		//listCustomers(customers);	
		
		// List customers with details
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);
		
		products = productDao.findAllWithDetail();
		listProductsWithDetail(products);
		
		orders = orderDao.findAllWithDetail();
		listOrdersWithDetail(orders);

		
		Customer customer;
		Product product;
		Order order;
		
		// Find customer by ID
		customer = customerDao.findById(1l);
		System.out.println("");
		System.out.println("Customer with id 1:" + customer);
		System.out.println("");	
		
		product = productDao.findById(1l);
		System.out.println("");
		System.out.println("Product with id 1:" + product);
		System.out.println("");		
		
		order = orderDao.findById(1l);
		System.out.println("");
		System.out.println("Order with id 1:" + product);
		System.out.println("");
		
		// Add new customer
		customer = new Customer();
		customer.setFirstName("Michael");
		customer.setLastName("Jackson");
		customer.setAddress("PlaceNear");
		customer.setEmail("ME@place.com");
		customer.setMobPhone(95);
		customer.setSpecialInstructions("GO TO THAT PLACE");
		customer.setCredit(200);
		customerDao.save(customer);
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);
		
		product = new Product();
		product.setCost(13);
		product.setName("Thing");
		productDao.save(product);
		products = productDao.findAllWithDetail();
		listProductsWithDetail(products);
		
		order = new Order();
		order.setCustomer(customer);
		order.setProduct(product);
		orderDao.save(order);
		
		order = new Order();
		order.setCustomer(customerDao.findById(3l));
		order.setProduct(productDao.findById(3l));
		orderDao.save(order);
		orders = orderDao.findAllWithDetail();
		listOrdersWithDetail(orders);

		// Update customer
		customer = customerDao.findById(1l);
		customer.setFirstName("Kim Fung");

		customerDao.save(customer);
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);
		
		product = productDao.findById(1l);
		product.setName("Changed name");
		
		productDao.save(product);
		products = productDao.findAllWithDetail();
		listProductsWithDetail(products);
		
		order = orderDao.findById(1l);
		order.setCustomer(customer);
		
		orderDao.save(order);
		orders = orderDao.findAllWithDetail();
		System.out.println("-----------------------------------------------------");
		listOrdersWithDetail(orders);
		
		//order = orderDao.findById(1l);
		
		// Delete customer
		customer = customerDao.findById(1l);
		customerDao.delete(customer);
		customers = customerDao.findAllWithDetail();
		listCustomersWithDetail(customers);	
		
		product = productDao.findById(1l);
		productDao.delete(product);
		products = productDao.findAllWithDetail();
		listProductsWithDetail(products);
		
		orders = orderDao.findAllWithDetail();
		System.out.println("0----------------------------0-------------------------0");
		listOrdersWithDetail(orders);
		order = orderDao.findById(2l);
		orderDao.delete(order);
		orders = orderDao.findAllWithDetail();
		System.out.println("1----------------------------1-------------------------1");
		listOrdersWithDetail(orders);
		
		
	}
	
	private static void listCustomers(List<Customer> customers) {
		System.out.println("");
		System.out.println("Listing customers without details:");
		for (Customer customer: customers) {
			System.out.println(customer);		
			System.out.println();	
		}
	}	
	
	private static void listCustomersWithDetail(List<Customer> customers) {
		System.out.println("");
		System.out.println("Listing customers with details:");
		for (Customer customer: customers) {
			System.out.println(customer);
			System.out.println();
		}		
	}	
	
	private static void listProductsWithDetail(List<Product> customers) {
		System.out.println("");
		System.out.println("Listing products with details:");
		for (Product customer: customers) {
			System.out.println(customer);
			System.out.println();
		}		
	}
	
	private static void listOrdersWithDetail(List<Order> customers) {
		System.out.println("");
		System.out.println("Listing orders with details:");
		for (Order customer: customers) {
			System.out.println(customer);
			System.out.println();
		}		
	}
}

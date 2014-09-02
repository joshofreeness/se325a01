package com.joshofreeness.ordertracking;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "ORDER")
public class Order implements Serializable{
	
	
	private static final long serialVersionUID = 1920435896091513789L;
	private Long id;
	private Customer customer;
	private Product product;
	final Logger log;
	
	public Order(Customer customer, Product product){
		log = Logger.getLogger(Product.class);
		this.setCustomer(customer);
		this.setProduct(product);
		log.info("New Order Created");
	}

	@Column(name = "CUSTOMER")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Column(name = "PRODUCT")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

package com.joshofreeness.ordertracking.service;

import java.util.List;

import com.joshofreeness.ordertracking.domain.Product;

public interface ProductService {

	public List<Product> findAll();

	public void delete(Product product);

	public Product findById(Long id);
	
	public Product save(Product product);
}

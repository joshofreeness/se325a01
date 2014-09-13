package com.joshofreeness.ordertracking.persistence;

import java.util.List;

import com.joshofreeness.ordertracking.domain.Product;

public interface ProductDao {
	
	public List<Product> findAll();

	public void delete(Product product);

	public Product findById(Long id);
	
	public Product save(Product product);

}

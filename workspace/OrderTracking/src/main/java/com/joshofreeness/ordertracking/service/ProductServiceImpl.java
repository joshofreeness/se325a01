package com.joshofreeness.ordertracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshofreeness.ordertracking.domain.Product;
import com.joshofreeness.ordertracking.persistence.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public void delete(Product product) {
		productDao.delete(product);
		
	}

	@Override
	public Product findById(Long id) {
		return productDao.findById(id);
	}

	@Override
	public Product save(Product product) {
		return productDao.save(product);
	}

}

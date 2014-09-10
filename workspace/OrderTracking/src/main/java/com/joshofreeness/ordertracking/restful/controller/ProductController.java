package com.joshofreeness.ordertracking.restful.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.joshofreeness.ordertracking.domain.Product;
import com.joshofreeness.ordertracking.domain.Products;
import com.joshofreeness.ordertracking.persistence.ProductDao;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductDao productDao; 

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Products listData(WebRequest webRequest) {
		return new Products(productDao.findAll());
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Product findProductById(@PathVariable Long id) {		
		return productDao.findById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Product create(@RequestBody Product product, HttpServletResponse response) {
		log.info("Creating Product: " + product);
		productDao.save(product);
		log.info("Product created successfully with info: " + product);
		response.setHeader("Location",  "/Products/" + product.getId());
		return product;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Product product, @PathVariable Long id) {
		log.info("Updating Product: " + product);
		productDao.save(product);
		log.info("Product updated successfully with info: " + product);
		//return Product;
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		log.info("Deleting Product with id: " + id);
		Product product = productDao.findById(id);
		productDao.delete(product);
		log.info("Product deleted successfully");
	}	


}

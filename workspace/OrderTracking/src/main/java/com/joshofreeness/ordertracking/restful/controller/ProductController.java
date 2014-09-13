package com.joshofreeness.ordertracking.restful.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joshofreeness.ordertracking.domain.Product;
import com.joshofreeness.ordertracking.persistence.ProductDao;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductDao productDao; 

//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public Products listData(WebRequest webRequest) {
//		return new Products(productDao.findAll());
//	}	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list( Model uiModel ) {
		List<Product> products = productDao.findAll( );
		uiModel.addAttribute( "products", products );
		return "products/list";
	 }

//	@RequestMapping(value="/{id}", method=RequestMethod.GET)
//	@ResponseBody
//	public Product findProductById(@PathVariable Long id) {		
//		return productDao.findById(id);
//	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String findProductById(@PathVariable Long id, Model uiModel) {		
		Product product = productDao.findById(id);
		uiModel.addAttribute( "product", product );
		return "products/show";
	}
	//Delete Product
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteProductById(@PathVariable Long id, Model uiModel){
		Product product = productDao.findById(id);
		productDao.delete(product);
		return "redirect:/products/";
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
	
	//Request a new Product Form
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		Product product = new Product();
		uiModel.addAttribute("product", product);
		return "products/create";
	}
	
	//Post a new product form to create new customer
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String createFromForm(Product product, BindingResult bindingResult, Model uiModel) {

		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("product", product);
			return "products/create";
		}
		log.info("Save product");
		productDao.save(product);
		log.info("Saved product");
		return "redirect:/products/" + product.getId();
	}
	
	//Request an edit product form
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		Product product = productDao.findById(id);
		uiModel.addAttribute("product", product);
		
		return "products/update";
	}
	
	//Post an edited product
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(Product product, BindingResult bindingResult, Model uiModel) {
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("product", product);

			return "products/update";
		}
		
		productDao.save(product);

		// Redirect the browser to a page that displays the updated Contact.
		return "redirect:/products/" + product.getId();
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

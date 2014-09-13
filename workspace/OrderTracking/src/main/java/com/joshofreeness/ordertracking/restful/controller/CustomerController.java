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

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.persistence.CustomerDao;


@Controller
@RequestMapping(value = "/customers")
public class CustomerController {
	
	final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerDao customerDao; 

//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public Customers listData(WebRequest webRequest) {
//		return new Customers(customerDao.findAll());
//	}	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list( Model uiModel ) {
		List<Customer> customers = customerDao.findAll( );
		uiModel.addAttribute( "customers", customers );
		return "customers/list";
	 }

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String findCustomerById(@PathVariable Long id, Model uiModel) {		
		Customer customer = customerDao.findById(id);
		uiModel.addAttribute( "customer", customer );
		return "customers/show";
	}
	
	//Delete Product
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteProductById(@PathVariable Long id, Model uiModel){
		Customer customer = customerDao.findById(id);
		customerDao.delete(customer);
		return "redirect:/customers/";
	}
		
		
	//Request a new Customer Form
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		Customer customer = new Customer();
		uiModel.addAttribute("customer", customer);
		return "customers/create";
	}
	//Post a new customer form to create new customer
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String createFromForm(Customer contact, BindingResult bindingResult, Model uiModel) {

		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("contact", contact);
			return "customers/create";
		}
		log.info("Save contact");
		customerDao.save(contact);
		log.info("Saved contact");
		return "redirect:/customers/" + contact.getId();
	}
	
	//Request an edit product form
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		Customer customer = customerDao.findById(id);
		uiModel.addAttribute("customer", customer);
		
		return "customers/update";
	}
	
	//Post an edited product
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(Customer customer, BindingResult bindingResult, Model uiModel) {
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("customer", customer);

			return "products/update";
		}
		
		customerDao.save(customer);

		// Redirect the browser to a page that displays the updated Contact.
		return "redirect:/customers/" + customer.getId();
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Customer create(@RequestBody Customer Customer, HttpServletResponse response) {
		log.info("Creating Customer: " + Customer);
		customerDao.save(Customer);
		log.info("Customer created successfully with info: " + Customer);
		response.setHeader("Location",  "/Customers/" + Customer.getId());
		return Customer;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Customer Customer, @PathVariable Long id) {
		log.info("Updating Customer: " + Customer);
		customerDao.save(Customer);
		log.info("Customer updated successfully with info: " + Customer);
		//return Customer;
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		log.info("Deleting Customer with id: " + id);
		Customer Customer = customerDao.findById(id);
		customerDao.delete(Customer);
		log.info("Customer deleted successfully");
	}	

}

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
import org.springframework.web.context.request.WebRequest;

import com.joshofreeness.ordertracking.domain.Customer;
import com.joshofreeness.ordertracking.domain.Order;
import com.joshofreeness.ordertracking.domain.Orders;
import com.joshofreeness.ordertracking.domain.Product;
import com.joshofreeness.ordertracking.persistence.CustomerDao;
import com.joshofreeness.ordertracking.persistence.OrderDao;
import com.joshofreeness.ordertracking.persistence.ProductDao;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {
	
	final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderDao orderDao; 
	@Autowired
	CustomerDao customerDao;
	@Autowired
	ProductDao productDao;

//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public Orders listData(WebRequest webRequest) {
//		return new Orders(orderDao.findAll());
//	}	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list( Model uiModel ) {
		List<Order> orders = orderDao.findAll( );
		uiModel.addAttribute( "orders", orders );
		return "orders/list";
	 }
	
	//Request a new Order Form
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		List<Product> products = productDao.findAll();
		List<Customer> customers = customerDao.findAll();
		Order order = new Order();
		uiModel.addAttribute("order", order);
		uiModel.addAttribute("products", products);
		uiModel.addAttribute("customers", customers);
		return "orders/create";
	}
	
	//Post a new product form to create new customer
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String createFromForm(Order order, BindingResult bindingResult, Model uiModel) {

		if(bindingResult.hasErrors()) {
			
			uiModel.addAttribute("error", bindingResult.getAllErrors());
			uiModel.addAttribute("order", order);
			List<Product> products = productDao.findAll();
			List<Customer> customers = customerDao.findAll();
			uiModel.addAttribute("products", products);
			uiModel.addAttribute("customers", customers);
			return "orders/create";
		}
		log.info("Save order");
		orderDao.save(order);
		log.info("Saved order");
		return "redirect:/orders/" + order.getId();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Order findCustomerById(@PathVariable Long id) {		
		return orderDao.findById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Order create(@RequestBody Order order, HttpServletResponse response) {
		log.info("Creating Order: " + order);
		orderDao.save(order);
		log.info("Order created successfully with info: " + order);
		response.setHeader("Location",  "/Orders/" + order.getId());
		return order;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Order order, @PathVariable Long id) {
		log.info("Updating Order: " + order);
		orderDao.save(order);
		log.info("Order updated successfully with info: " + order);
		//return Customer;
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		log.info("Deleting Order with id: " + id);
		Order order = orderDao.findById(id);
		orderDao.delete(order);
		log.info("Order deleted successfully");
	}	

}
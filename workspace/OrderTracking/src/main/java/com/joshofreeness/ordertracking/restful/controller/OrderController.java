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

import com.joshofreeness.ordertracking.domain.Order;
import com.joshofreeness.ordertracking.domain.Orders;
import com.joshofreeness.ordertracking.persistence.OrderDao;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {
	
	final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderDao orderDao; 

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Orders listData(WebRequest webRequest) {
		return new Orders(orderDao.findAll());
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
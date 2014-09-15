package com.joshofreeness.ordertracking.restful.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
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
import com.joshofreeness.ordertracking.domain.Order;
import com.joshofreeness.ordertracking.domain.Product;
import com.joshofreeness.ordertracking.persistence.CustomLogger;
import com.joshofreeness.ordertracking.persistence.CustomerDao;
import com.joshofreeness.ordertracking.persistence.OrderDao;
import com.joshofreeness.ordertracking.persistence.ProductDao;
import com.joshofreeness.ordertracking.service.CustomerService;
import com.joshofreeness.ordertracking.service.OrderService;
import com.joshofreeness.ordertracking.service.ProductService;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {
	
	final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService; 
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;

//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public Orders listData(WebRequest webRequest) {
//		return new Orders(orderDao.findAll());
//	}	
	
	@PostConstruct
	public void setup(){
		ProxyFactory proxFact = new ProxyFactory();
		proxFact.setTarget(customerService);
		proxFact.addAdvice(new CustomLogger());
		customerService = (CustomerService) proxFact.getProxy();
		
		ProxyFactory proxFact1 = new ProxyFactory();
		proxFact1.setTarget(orderService);
		proxFact1.addAdvice(new CustomLogger());
		orderService = (OrderService) proxFact1.getProxy();
		
		ProxyFactory proxFact2 = new ProxyFactory();
		proxFact2.setTarget(productService);
		proxFact2.addAdvice(new CustomLogger());
		productService = (ProductService) proxFact2.getProxy();
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list( Model uiModel ) {
		List<Order> orders = orderService.findAll( );
		uiModel.addAttribute( "orders", orders );
		return "orders/list";
	 }
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String findOrderrById(@PathVariable Long id, Model uiModel) {		
		Order order = orderService.findById(id);
		uiModel.addAttribute( "order", order );
		return "orders/show";
	}
	
	//Request a new Order Form
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		List<Product> products = productService.findAll();
		List<Customer> customers = customerService.findAll();
		IdParserForNewOrder id_object = new IdParserForNewOrder();
		uiModel.addAttribute("products", products);
		uiModel.addAttribute("customers", customers);
		uiModel.addAttribute("id_object",id_object);
		return "orders/create";
	}
	
	//Post a new product form to create new order
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String createFromForm(IdParserForNewOrder ids, BindingResult bindingResult, Model uiModel) {

		if(bindingResult.hasErrors()) {
			
			uiModel.addAttribute("error", bindingResult.getAllErrors());
			List<Product> products = productService.findAll();
			List<Customer> customers = customerService.findAll();
			IdParserForNewOrder id_object = new IdParserForNewOrder();
			uiModel.addAttribute("products", products);
			uiModel.addAttribute("customers", customers);
			uiModel.addAttribute("id_object", id_object);
			return "orders/create";
		}
		log.info("Save order");
		Order order = new Order();
		order.setCustomer(customerService.findById(new Long(ids.getCustomer())));
		order.setProduct(productService.findById(new Long(ids.getProduct())));
		orderService.save(order);
		log.info("Saved order");
		return "redirect:/orders/" + order.getId();
	}

//	@RequestMapping(value="/{id}", method=RequestMethod.GET)
//	@ResponseBody
//	public Order findCustomerById(@PathVariable Long id) {		
//		return orderDao.findById(id);
//	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public Order create(@RequestBody Order order, HttpServletResponse response) {
		log.info("Creating Order: " + order);
		orderService.save(order);
		log.info("Order created successfully with info: " + order);
		response.setHeader("Location",  "/Orders/" + order.getId());
		return order;
	}
	
	//Delete Order
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteProductById(@PathVariable Long id, Model uiModel){
		Order order = orderService.findById(id);
		orderService.delete(order);
		return "redirect:/orders/";
	}
	
	//Request an edit order form
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		Order order = orderService.findById(id);
		IdParserForNewOrder order_info = new IdParserForNewOrder();
		order_info.setCustomer((int)(long)order.getCustomer().getId());
		order_info.setProduct((int)(long)order.getProduct().getId());
		order_info.setId(order.getId());
		List<Customer> customers = customerService.findAll();
		List<Product> products = productService.findAll();
		uiModel.addAttribute("customers", customers);
		uiModel.addAttribute("products", products);
		uiModel.addAttribute("id_object", order_info);
		
		
		return "orders/update";
	}
	
	//Post an edited order
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(IdParserForNewOrder order_info, BindingResult bindingResult, Model uiModel) {
		if(bindingResult.hasErrors()) {
			uiModel.addAttribute("order_info", order_info);

			return "orders/update";
		}
		
		Order order = orderService.findById(order_info.getId());
		order.setCustomer(customerService.findById(new Long(order_info.getCustomer())));
		order.setProduct(productService.findById(new Long(order_info.getProduct())));
		order.setId(order_info.getId());
		orderService.save(order);

		// Redirect the browser to a page that displays the updated Contact.
		return "redirect:/orders/" + order.getId();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody Order order, @PathVariable Long id) {
		log.info("Updating Order: " + order);
		orderService.save(order);
		log.info("Order updated successfully with info: " + order);
		//return Customer;
	}	

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Long id) {
		log.info("Deleting Order with id: " + id);
		Order order = orderService.findById(id);
		orderService.delete(order);
		log.info("Order deleted successfully");
	}	

}
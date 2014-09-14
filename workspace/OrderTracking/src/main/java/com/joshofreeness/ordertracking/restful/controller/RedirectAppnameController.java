package com.joshofreeness.ordertracking.restful.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping(value = "*")
public class RedirectAppnameController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String redirect( Model uiModel ) {
		return "redirect:orders";
	 }

}

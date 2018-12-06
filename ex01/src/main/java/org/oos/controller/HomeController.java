package org.oos.controller;

import java.util.ArrayList;
import java.util.List;

import org.oos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {  
	
	@Setter(onMethod_=@Autowired)
	private ProductService service;
	
	@GetMapping("/pay")
	public void pay(Model model){
		
		log.info("payPage....");
//		model.addAttribute("product", service.read(1L));
	
	}
	@GetMapping("/test")
	public void test(Model model){
		
		log.info("payPage....");

	
	}
	@PostMapping("/autocomplete")
	@ResponseBody
	public List<String> autoComplete() {
		
		List<String> list=service.getName();
		
		
		log.info(""+list);
		return list;
	}

}
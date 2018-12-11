package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {  
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@GetMapping("/aboutus")
	public void aboutus(Model model) {
		
		
	}
	
	@GetMapping("/pay")
	public void pay(Model model){
		
		log.info("payPage....");
	}
	
	@GetMapping("/main")
	public void submain(Model model) {
		Map<String, Object> map = new HashMap<>();
		
		Criteria cri = new Criteria();
		PageDTO pageDTO = new PageDTO(cri, productService.getTotal(map));
		map.put("dto", pageDTO);
		
		log.info(productService.getList(map)+"");
		model.addAttribute("product", productService.getList(map));
	}
	
	@GetMapping("/oos")
	public void main() {
		
	}

}
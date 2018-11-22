package org.oos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/user/*")
@Log
public class ShopController {
	
	@GetMapping({"/main","/list","/productDetail","/productOrder","/cart","/store"})
	public void list() {
		
	}
}

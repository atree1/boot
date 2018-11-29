package org.oos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.CartVO;
import org.oos.domain.Criteria;
import org.oos.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/user/*")
public class UserController {

	@Setter(onMethod_=@Autowired)
	private CartService cartService;
	
	@GetMapping("/cart")
	public void list(String mid, Model model) {
		Criteria cri=new Criteria();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("criteria", cri);
		map.put("mid",mid);
		log.info(""+cartService.getList(map));
		
		model.addAttribute("cartList",cartService.getList(map));
	}
	@GetMapping("/login")
	public void login() {
		
	}
}

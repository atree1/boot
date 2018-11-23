package org.oos.controller;

import org.oos.service.MemberService;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/order/*")
public class OrderController {
	
	@Setter(onMethod_=@Autowired)
	private OrderService orderService;
	
	@Setter(onMethod_=@Autowired)
	private OrderDetailService orderDetailService;
	
	@Setter(onMethod_=@Autowired)
	private MemberService memberService;

	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@GetMapping("/success")
	public void successGET(Long ono, Model model) {
		
		model.addAttribute("order", orderService.get(ono));
	}
	
	@GetMapping("/list")
	public void orderListGET(String mid, Long sno, Model model) {
		
		model.addAttribute("Member", memberService.get(mid));
		model.addAttribute("Store", storeService.get(sno));
		
	}

}

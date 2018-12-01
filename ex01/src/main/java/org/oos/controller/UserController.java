package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.service.CartService;
import org.oos.service.MemberService;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/user/*")
public class UserController {

	@Setter(onMethod_=@Autowired)
	private CartService cartService;
	
	@Setter(onMethod_= @Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_= @Autowired)
	private OrderDetailService orderDetailService;
	
	@Setter(onMethod_= @Autowired)
	private OrderService orderService;
	
	@Setter(onMethod_= @Autowired)
	private StoreService storeService;
	
	@GetMapping("/mypage/orderDetail")
	public void orderDetailList(String mid, Model model) {
		Criteria cri=new Criteria();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cri", cri);
		map.put("mid",mid);

		model.addAttribute("orderDetail", orderService.getList(map));

	}
	
	@GetMapping("/mypage/modify")
	public void get(String mid,Model model) {
		model.addAttribute("member",memberService.get(mid));
	}
	@PostMapping("/mypage/modify")
	public String modify(MemberVO vo) {
		log.info(""+vo);
		log.info(""+memberService.modify(vo));
		
		return "redirect:/store/list?sno=1";
	}
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

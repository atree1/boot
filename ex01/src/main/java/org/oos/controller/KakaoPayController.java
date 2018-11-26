package org.oos.controller;



import java.util.List;
import java.util.Map;

import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;
import org.oos.service.KakaopayService;
import org.oos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;


@Controller
@RequestMapping("/kakaopay/*")
@Log
public class KakaoPayController {
	@Setter(onMethod_ = @Autowired)
	private KakaopayService service;
	
	@Setter(onMethod_ = @Autowired)
	private OrderService orderService;
	
	@PostMapping(value="/pay",
			consumes="application/json")
	public ResponseEntity<String> kakaoPay(@RequestBody List<OrderDetailVO> orderList) {

		return new ResponseEntity<>(service.kakaoPayReady(orderList), HttpStatus.OK);
	}
	@GetMapping("/success")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		Map<String, Object> map = service.kakaoPayInfo(pg_token);
		List<OrderDetailVO> list = (List<OrderDetailVO>) map.get("orderList");
		
		OrderVO order = new OrderVO();
		order.setMid(list.get(0).getMid());
		order.setSno(list.get(0).getSno());
		
		
		orderService.insert(order, list);
		
		
		model.addAttribute("info", map.get("kakao"));
	}
	@GetMapping("/fail")
	public void fail() {
		log.info("kakaoPayFail get............................................");

		
	}
	@GetMapping("/cancle")
	public void cancle() {
		log.info("kakaoPayCancle get............................................");

		
	}
}

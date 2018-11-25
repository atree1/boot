package org.oos.controller;



import org.oos.domain.OrderDetailVO;
import org.oos.service.KakaopayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/pay")
	public String kakaoPay(String mid,OrderDetailVO orderList) {
		log.info("kakaoPay post............................................");
		log.info("member:"+mid);  
		log.info("orderList:"+orderList);
		return "redirect:"+service.kakaoPayReady();

	}
	@GetMapping("/success")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		log.info("kakaoPaySuccess get............................................");
		log.info("kakaoPaySuccess pg_token : " + pg_token);
		model.addAttribute("info", service.kakaoPayInfo(pg_token));
		
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

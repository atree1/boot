package org.oos.controller;

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
@RequestMapping("/kakao/*")
@Log
public class KakaoPayController {
	@Setter(onMethod_ = @Autowired)
	private KakaopayService service;
	
	@PostMapping("/pay")
	public String kakaoPay() {
		log.info("kakaoPay post............................................");
		
		return "redirect:" + service.kakaoPayReady();

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

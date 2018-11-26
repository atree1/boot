package org.oos.controller;



import java.util.List;

import org.oos.domain.KakaoPayReadyDTO;
import org.oos.domain.OrderDetailVO;
import org.oos.service.KakaopayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	@CrossOrigin
	@PostMapping(value="/pay",consumes = "application/json; charset=utf-8" )
	public ResponseEntity<String> kakaoPay(@RequestBody List<OrderDetailVO> orderList) {
		log.info("kakaoPay post............................................");
		
		log.info("orderList:"+orderList);
		return new ResponseEntity<>(service.kakaoPayReady(orderList),HttpStatus.OK);

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

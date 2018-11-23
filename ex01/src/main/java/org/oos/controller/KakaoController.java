package org.oos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakao/*")
public class KakaoController {
	@GetMapping("/payment")
	public void kakao() {
		
	}
	
	@GetMapping("/order")
	public void order() {
		
	}
}

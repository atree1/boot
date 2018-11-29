package org.oos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oos.domain.MemberVO;
import org.oos.service.KakaoLoginService;
import org.oos.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class KakaoLoginController {

	@Setter(onMethod_=@Autowired)
	private KakaoLoginService kakaoService;

	@Setter(onMethod_=@Autowired)
	private MemberService memberService;
	@GetMapping("/kakaologin")
	@RequestMapping(value = "/kakaologin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public String kakaoLogin(@RequestParam("code") String code) throws Exception{

	  JsonNode token =kakaoService.getAccessToken(code);
	  log.info("=====" + code);
	  log.info("=====" + token);
	  
	  JsonNode profile = kakaoService.getKakaoUserInfo(token.path("access_token").toString());
	  System.out.println("profile: " + profile);
//	  
//	  MemberVO vo = kakaoService.changeData(profile);
	 // memberService.register(vo);
	  
	 // System.out.println(vo.toString());
		  
	  return "redirect:/store/list?sno=1";
	}
}

package org.oos.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.oos.domain.OrderDetailVO;
import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;
import org.oos.service.MemberService;
import org.oos.service.OrderDetailService;
import org.oos.service.OrderService;
import org.oos.service.ProductService;
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
	
	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@GetMapping("/success")
	public void successGET(Long ono, Model model) {
		
		model.addAttribute("order", orderService.get(ono));
	}
	
	@GetMapping("/list")
	public void orderListGET(Long pno,String mid, Long sno,String[] info, Model model) {
		
		log.info("------------------------------------------");
		log.info("info: "+Arrays.toString(info));
		
		List<OrderDetailVO> list=new ArrayList<>();
		for (String size : info) {
			String[] sizeInfo=size.split("_");
			OrderDetailVO vo=new OrderDetailVO();			
			
			ProductVO pVO=productService.read(pno);
			pVO.getOptList().forEach(opt->{
				if(opt.getSize().equals(sizeInfo[0])) {
					vo.setOpno(opt.getOpno());	
				}
			});
			vo.setPno(pno);
			vo.setProduct(pVO);
			vo.setQty(Long.parseLong(sizeInfo[1]));
			
			list.add(vo);
			log.info(""+vo);
		}
		model.addAttribute("Member", memberService.get(mid));
		model.addAttribute("Store", storeService.get(sno));
		model.addAttribute("orderList",list);
	}

}

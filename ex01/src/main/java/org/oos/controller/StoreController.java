package org.oos.controller;

import java.util.HashMap;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.ProductVO;
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
@RequestMapping("/store/*")
public class StoreController {

	@Setter(onMethod_=@Autowired)
	private ProductService productService;
	
	@Setter(onMethod_=@Autowired)
	private StoreService storeService;
	
	@GetMapping("/list")
	public void storeList(Criteria cri,Long sno, Model model) {
		log.info(cri+"");
		Map<String, Object> map = new HashMap<>();
		map.put("criteria", cri);
		map.put("sno", sno);
		
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", productService.getList(map));
	}
	
	@GetMapping("/detail")
	public void productRead(Long pno, Model model) {
		
		ProductVO vo = productService.read(pno);
		model.addAttribute("store", storeService.get(vo.getSno()));
		model.addAttribute("product", vo);
	}
}

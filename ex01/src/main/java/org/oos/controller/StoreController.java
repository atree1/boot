package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductVO;
import org.oos.mapper.ImgurMapper;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@Setter(onMethod_= @Autowired)
    private	ImgurMapper imgurMapper;
	
	@GetMapping("/list")
	public void storeList(Criteria cri,Long sno, Model model) {
		
		Map<String, Object> map = new HashMap<>();
		PageDTO dto = new PageDTO(cri, productService.getTotal(map));
		
		map.put("dto", dto);
		map.put("sno", sno);
		
		PageDTO pageDTO = new PageDTO(cri, productService.getTotal(map));
        
        List<Integer> pageList = new ArrayList<>();
        
        for(int i=pageDTO.getStartPage(); i<=pageDTO.getEndPage(); i++) {
            pageList.add(i);
        }
        
        model.addAttribute("img", imgurMapper.getList());
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageMaker", pageDTO);
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", productService.getList(map));
	}
	
	@GetMapping("/detail")
	public void productRead(Long pno, Long sno,  Model model) {
		
		ProductVO vo = productService.read(pno);
		model.addAttribute("img", imgurMapper.getList());
		model.addAttribute("store", storeService.get(sno));
		model.addAttribute("product", vo);
	}
	
	@PostMapping("/autocomplete")
    @ResponseBody
    public List<String> autoComplete() {
        
        List<String> list=storeService.getName();
        return list;
    }
}

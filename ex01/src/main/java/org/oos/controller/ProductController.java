package org.oos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;
import org.oos.service.AutoMlService;
import org.oos.service.ProductService;
import org.oos.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@Setter(onMethod_ = @Autowired)
	private StoreService storeService;
	@Setter(onMethod_ = @Autowired)
	private AutoMlService autoMlService;
	
	@GetMapping("/register")
	public void productRegister(Long sno, Model model) {

		model.addAttribute("store", storeService.get(sno));
	}

	@PostMapping("/register")
	public String productRegisterPost(ProductVO vo, String[] size, Long[] qty, RedirectAttributes rttr, Long sno) {
		log.info("========================================================================");
		List<ProductOptionVO> optList = new ArrayList<>();
		for (int i = 0; i < size.length; i++) {
			ProductOptionVO optVO = new ProductOptionVO();
			optVO.setPno(vo.getPno());
			optVO.setSize(size[i]);
			optVO.setQty(qty[i]);
			optList.add(optVO);
		}
		log.info("========================================================================");
		vo.getImgList().forEach(img -> {
			String path = "C:\\upload\\"+img.getIpath() + "\\" + img.getUuid() + "_" + img.getIname();
			log.info("=====================================================");
			log.info(path);
			try {
				autoMlService.predict("oos-atree-224402", "us-central1", "ICN8398129299585242244", path, "0.7");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("=====================================================");
		});
		vo.setOptList(optList);
		int result = productService.register(vo);
		log.info("" + vo);
		log.info("" + result);

		rttr.addFlashAttribute("result", result == 1 ? "SUCCESS" : "FAIL");

		return "redirect:/store/list?sno=" + storeService.get(sno).getSno();
	}

}
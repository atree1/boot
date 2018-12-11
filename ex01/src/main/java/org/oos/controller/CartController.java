package org.oos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.CartVO;
import org.oos.domain.Criteria;
import org.oos.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/cart")
@Log
public class CartController {
	@Setter(onMethod_=@Autowired)
	private CartService service;
	
	
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody List<CartVO> vo) {
		
		log.info("Cart vo :" + vo);

		int result = service.register(vo);
				
		return result == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
/*	@GetMapping(value="/pages/{mid}/{page}",produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<CartVO>> getList(@PathVariable("page")int pageNum,@PathVariable("mid") String mid,Model model){
		
		Criteria cri=new Criteria();
		cri.setPageNum(pageNum);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("criteria", cri);
		map.put("mid",mid);
		return new ResponseEntity<>(service.getList(map),HttpStatus.OK);
	}
	
	@GetMapping(value="/{cno}",produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<CartVO> get(@PathVariable("cno")Long cno){
		
		return new ResponseEntity<CartVO>(service.get(cno),HttpStatus.OK);
	}
	
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH},value="/{cno}",consumes="application/json",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody CartVO vo,@PathVariable("cno") Long cno){

		vo.setCno(cno);
		int result=service.modify(vo);
		return result==1?new ResponseEntity<String>("success",HttpStatus.OK):new ResponseEntity<String> (HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@RequestMapping(value="/delete/{str}")
	
	public ResponseEntity<List<CartVO>> delete(@PathVariable("str") String str, Model model){
		int result = -1;
		
		Criteria cri=new Criteria();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("criteria", cri);
		map.put("mid","test");
		
		String[] list = str.split("_");
		
		for(String cno : list) {
			result = service.remove(Long.parseLong(cno));
		}
		
		return result==1?new ResponseEntity<>(service.getList(map),HttpStatus.OK):new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

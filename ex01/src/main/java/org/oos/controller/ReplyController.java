package org.oos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.Criteria;
import org.oos.domain.ReplyPageDTO;
import org.oos.domain.ReplyVO;
import org.oos.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.java.Log;

@RequestMapping("/replies")
@RestController
@Log
public class ReplyController {
	
	@Setter(onMethod_=@Autowired)	
	private ReplyService service;
	
	//댓글등록
	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		int result = service.register(vo);
		
		return result == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//리스트조회
	@GetMapping(value="/pages/{kind}/{pno}/{page}")
	public ReplyPageDTO getList(@PathVariable("page") int pageNum, @PathVariable("pno") Long pno, @PathVariable("kind") char kind ){
	
		Criteria cri = new Criteria(pageNum,10);
				
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("cri", cri);
		map.put("pno", pno);
		map.put("kind", kind);
		
		return service.getListPage(map);
	}
	
	
	//댓글조회
	@GetMapping(value="/{rno}")
	public ReplyVO get(@PathVariable("rno") Long rno){
		
		return service.get(rno);
	}
	
	//댓글삭제
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH}, value="/delete/{rno}", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
	
		int result = service.remove(rno);
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글수정
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH}, value="/{rno}", consumes="application/json", produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){

		vo.setRno(rno);
		
		int result = service.modify(vo);
		
		return result == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
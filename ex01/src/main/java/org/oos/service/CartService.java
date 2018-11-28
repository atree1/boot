package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.CartVO;
import org.oos.domain.Criteria;


public interface CartService {

	public List<CartVO> getList(Map<String, Object> map);
	
	public CartVO get(Long cno);
	
	public int register(List<CartVO> list);
	
	public int modify(CartVO vo);
	
	public int remove(Long cno);
		
	public int count(Map<String, Object> map);
}

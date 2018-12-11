package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.OrderDetailVO;

public interface OrderDetailService {
	
	public List<OrderDetailVO> getList(Long ono);
	
	public int count(Map<String, Object> map);
	
	public OrderDetailVO get(Long odno);
	
	public int modify(OrderDetailVO vo);
	
	public int delete(Long odno);
	
	public int insert(OrderDetailVO vo);
}

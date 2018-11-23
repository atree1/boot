package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.oos.domain.OrderDetailVO;

public interface OrderDetailMapper {

	public List<OrderDetailVO> getList(Long ono);

	public OrderDetailVO get(Long odno);
	
	public int modify(OrderDetailVO vo);
	
	public int delete(Long odno);
	
	public int insert(OrderDetailVO vo);
}

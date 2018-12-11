package org.oos.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.domain.OrderDetailVO;
import org.oos.domain.OrderVO;
import org.oos.mapper.OrderDetailMapper;
import org.oos.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class OrderServiceImpl implements OrderService {
	
	@Setter(onMethod_=@Autowired)
	private OrderMapper orderMapper;
	
	@Setter(onMethod_=@Autowired)
	private OrderDetailMapper orderDetailMapper;
	
	@Override
	public List<OrderVO> getList(Map<String, Object> map){
		
		return orderMapper.getList(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		
		return orderMapper.count(map);
	}

	@Override
	public OrderVO get(Long ono) {
		
		return orderMapper.get(ono);
	}

	@Transactional
	@Override
	public int insert(OrderVO order, List<OrderDetailVO> detail) {
		int count = orderMapper.insert(order);
		
		detail.forEach(detailvo -> {
			detailvo.setOno(order.getOno());
			orderDetailMapper.insert(detailvo);
		});
		
		return count;
	}

	@Override
	public int modify(OrderVO vo) {
		
		return orderMapper.modify(vo);
	}

	@Transactional
	@Override
	public int delete(Long odno) {
		
		return orderDetailMapper.delete(odno);
	}
	
}

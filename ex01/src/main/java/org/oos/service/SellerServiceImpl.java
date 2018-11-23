package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;
import org.oos.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class SellerServiceImpl implements SellerService {
	
	@Setter(onMethod_=@Autowired)
	private SellerMapper mapper;

	@Override
	public List<SellerVO> getList(Criteria cri) {
		
		return mapper.getList(cri);
	}

	@Override
	public SellerVO get(String sid) {
		// TODO Auto-generated method stub
		return mapper.get(sid);
	}


	@Override
	public int modify(SellerVO vo) {
		// TODO Auto-generated method stub
		return mapper.modify(vo);
	}

	@Override
	public int remove(String sid) {
		// TODO Auto-generated method stub
		return mapper.delete(sid);
	}

	@Override
	public int count(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.count(cri);
	}

	@Override
	public int register(SellerVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

}

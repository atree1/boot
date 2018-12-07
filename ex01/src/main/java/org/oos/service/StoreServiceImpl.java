package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.StoreVO;
import org.oos.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class StoreServiceImpl implements StoreService {

	@Setter(onMethod_=@Autowired)
	private StoreMapper mapper;
	
	@Override
	public List<StoreVO> getList(Criteria cri) {
		
		return mapper.getList(cri);
	}

	@Override
	public StoreVO get(Long sno) {
		// TODO Auto-generated method stub
		return mapper.get(sno);
	}

	@Override
	public int register(StoreVO vo) {
		
		return mapper.insert(vo);
	}

	@Override
	public int remove(Long sno) {
		// TODO Auto-generated method stub
		return mapper.delete(sno);
	}

	@Override
	public int modify(StoreVO vo) {
		// TODO Auto-generated method stub
		return mapper.modify(vo);
	}

	@Override
	public int count(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.count(cri);
	}

	@Override
	public List<String> getName() {
		// TODO Auto-generated method stub
		return mapper.getName();
	}

}

package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;
import org.oos.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;



@Service
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod_= @Autowired)
	private MemberMapper mapper;
	
	@Override
	public List<MemberVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getList(cri);
	}

	@Override
	public MemberVO get(String mid) {
		// TODO Auto-generated method stub
		return mapper.get(mid);
	}

	@Override
	public int register(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int modify(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.modify(vo);
	}

	@Override
	public int remove(String mid) {
		// TODO Auto-generated method stub
		return mapper.delete(mid);
	}

	@Override
	public int count(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.count(cri);
	}

}

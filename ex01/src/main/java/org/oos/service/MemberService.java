package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.MemberVO;

public interface MemberService {

	public List<MemberVO> getList(Criteria cri);
	
	public MemberVO get(String mid);
	
	public int register(MemberVO vo);
	
	public int modify(MemberVO vo);
	
	public int remove(String mid);
		
	public int count(Criteria cri);
}

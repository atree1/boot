package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.SellerVO;

public interface SellerService {
	
	public List<SellerVO> getList(Criteria cri);
	
	public SellerVO get(String sid);
	
	public int register(SellerVO vo);
	
	public int modify(SellerVO vo);
	
	public int remove(String sid);
	
	public int count(Criteria cri);

}

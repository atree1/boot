package org.oos.mapper;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.StoreVO;

public interface StoreMapper {
	
	public List<StoreVO> getList(Criteria cri);
	
	public StoreVO get(Long sno);
	
	public int insert(StoreVO vo);
	
	public int modify(StoreVO vo);
	
	public int delete(Long sno);
	
	public int count(Criteria cri);
	public List<String> getName();
}

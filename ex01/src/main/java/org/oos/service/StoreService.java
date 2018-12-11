package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductImgVO;
import org.oos.domain.StoreVO;

public interface StoreService {
	
	public List<StoreVO> getList(PageDTO dto);
	
	public List<String> getName();
	
	public ProductImgVO getImg(Long sno);
	
	public StoreVO get(Long sno);
	
	public int register(StoreVO vo);
	
	public int remove(Long sno);
	
	public int modify(StoreVO vo);
	
	public int count(Criteria cri);
}

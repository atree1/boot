package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.ProductVO;

public interface ProductService {

	
	public List<ProductVO> getList(Map<String, Object> map);
	public ProductVO read(Long pno);
	public int update(ProductVO vo);
	public int remove(ProductVO vo);
	public int register(ProductVO vo);
	public int getTotal(Map<String, Object> map);
	public List<String> getName();
}

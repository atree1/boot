package org.oos.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.ProductVO;
 
public interface ProductMapper {

	public List<ProductVO> getList(Map<String, Object> map);
	public ProductVO get(Long pno);
	public int modify(ProductVO vo);
	public int delete(ProductVO vo);
	public int insert(ProductVO vo);
	public int count(Long sno);
}

package org.oos.mapper;

import java.util.List;

import org.oos.domain.ProductImgVO;

public interface ProductImgMapper {
	public List<ProductImgVO> getList(Long pno);
	public int insert(ProductImgVO vo);
	public int delete(String uuid);
	public void deleteAll(Long pno);
	
}

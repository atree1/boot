package org.oos.mapper;

import org.oos.domain.ProductImgVO;

public interface StoreImgMapper {
	
	public ProductImgVO get(Long sno);
	
	public int insert(ProductImgVO vo);
	
	public int delete(String uuid);
}

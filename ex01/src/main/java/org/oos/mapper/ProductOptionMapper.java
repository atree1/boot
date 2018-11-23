package org.oos.mapper;

import java.util.List;

import org.oos.domain.ProductOptionVO;

public interface ProductOptionMapper {
	public List<ProductOptionVO> getList(Long pno);
	public ProductOptionVO get(Long opno);
	public int modifyQty(ProductOptionVO vo);
	public int insert(ProductOptionVO vo);
}

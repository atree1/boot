package org.oos.mapper;

import java.util.List;

import org.oos.domain.ImgurVO;

public interface ImgurMapper {
	
	public void insert(Long pno, String img);
	
	public List<ImgurVO> getList();
}

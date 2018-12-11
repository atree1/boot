package org.oos.service;

import java.util.List;

import org.oos.domain.Criteria;
import org.oos.domain.PageDTO;
import org.oos.domain.ProductImgVO;
import org.oos.domain.StoreVO;
import org.oos.mapper.StoreImgMapper;
import org.oos.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class StoreServiceImpl implements StoreService {

	@Setter(onMethod_=@Autowired)
	private StoreMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private StoreImgMapper imgMapper;
	
	@Override
	public List<StoreVO> getList(PageDTO dto) {
		return mapper.getList(dto);
	}

	@Override
	public StoreVO get(Long sno) {
		return mapper.get(sno);
	}

	@Override
	public int register(StoreVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int remove(Long sno) {
		return mapper.delete(sno);
	}

	@Override
	public int modify(StoreVO vo) {
		return mapper.modify(vo);
	}

	@Override
	public int count(Criteria cri) {
		return mapper.count(cri);
	}

	@Override
	public ProductImgVO getImg(Long sno) {
		return imgMapper.get(sno);
	}
	
	@Override
    public List<String> getName() {
        return mapper.getName();
    }

}

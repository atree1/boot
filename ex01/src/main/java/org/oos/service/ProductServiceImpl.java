package org.oos.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.domain.ProductImgVO;
import org.oos.domain.ProductOptionVO;
import org.oos.domain.ProductVO;
import org.oos.mapper.ProductImgMapper;
import org.oos.mapper.ProductMapper;
import org.oos.mapper.ProductOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
@Service
public class ProductServiceImpl implements ProductService {

	@Setter(onMethod_=@Autowired)
	private ProductMapper pMapper;
	
	@Setter(onMethod_=@Autowired)
	private ProductImgMapper imgMapper;
	
	@Setter(onMethod_=@Autowired)
	private ProductOptionMapper optMapper;
	
	@Override
    public List<String> getName() {
        return pMapper.getName();
    }
	
	@Override
	public List<ProductVO> getList(Map<String, Object> map) {
		List<ProductVO> list= pMapper.getList(map);
		
		for (ProductVO vo : list) {
			
			vo.setOptList(optMapper.getList(vo.getPno()));
			vo.setImgList(imgMapper.getList(vo.getPno()));
			
		}
		
		return list;
	}
	
	@Override
	public ProductVO read(Long pno) {
	
		ProductVO vo=pMapper.get(pno);
		vo.setOptList(optMapper.getList(pno));
		vo.setImgList(imgMapper.getList(pno));
		
		return vo;
	}

	@Override
	@Transactional
	public int update(ProductVO vo) {

		imgMapper.deleteAll(vo.getPno());

		if (vo.getImgList() == null) {
			return pMapper.modify(vo);
		}
		if (vo.getImgList().size() > 0) {
			vo.getImgList().forEach(attach -> {
				attach.setPno(vo.getPno());
				imgMapper.insert(attach);
			});
		}
		
		return pMapper.modify(vo);
		
	}

	@Override
	public int remove(ProductVO vo) {
		return pMapper.delete(vo);
	}

	
	@Override
	@Transactional
	public int register(ProductVO vo) {
		int result=pMapper.insert(vo);
		
		for (ProductImgVO img:vo.getImgList()) {
			img.setPno(vo.getPno());
			imgMapper.insert(img);
		}
		for (ProductOptionVO opt:vo.getOptList()) {
			opt.setPno(vo.getPno());
			optMapper.insert(opt);
		}
		
		return result;
		
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return pMapper.count(map);
	}

}

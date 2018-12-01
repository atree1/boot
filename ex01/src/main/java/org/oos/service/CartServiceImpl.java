package org.oos.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.oos.domain.CartVO;
import org.oos.mapper.CartMapper;
import org.oos.mapper.ProductImgMapper;
import org.oos.mapper.ProductMapper;
import org.oos.mapper.ProductOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class CartServiceImpl implements CartService {
	@Setter(onMethod_=@Autowired)
	private CartMapper cartMapper;
	@Setter(onMethod_=@Autowired)
	private ProductMapper productMapper;
	@Setter(onMethod_=@Autowired)
	private ProductOptionMapper optMapper;
	@Setter(onMethod_=@Autowired)
	private ProductImgMapper imgMapper;
	
	@Override
    public List<CartVO> getList(Map<String, Object> map) {
        List<CartVO> list=cartMapper.getList(map);
        for (CartVO vo : list) {
            
            vo.setProduct(productMapper.get(vo.getPno()));
            vo.setOpt(optMapper.get(vo.getOpno()));
            vo.getProduct().setImgList(imgMapper.getList(vo.getPno()));
        }
        return list;
    }

	@Override
	@Transactional
	public CartVO get(Long cno) {
		CartVO vo=cartMapper.get(cno);
		vo.setOpt(optMapper.get(vo.getOpno()));
		vo.setProduct(productMapper.get(vo.getPno()));
		vo.getProduct().setImgList(imgMapper.getList(vo.getPno()));
		return vo;
	}

	@Override
	public int register(List<CartVO> list) {
		
		list.forEach(vo -> {
			cartMapper.insert(vo);
		});
		
		return 1;
	}

	@Override
	public int modify(CartVO vo) {
		return cartMapper.modify(vo);
	}

	@Override
	public int remove(Long cno) {
		return cartMapper.delete(cno);
	}

	@Override
	public int count(Map<String, Object> map) {
		return cartMapper.count(map);
	}

}

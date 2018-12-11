package org.oos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oos.domain.AmountVO;
import org.oos.domain.Criteria;
import org.oos.domain.ReplyPageDTO;
import org.oos.domain.ReplyVO;
import org.oos.mapper.ProductMapper;
import org.oos.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private ProductMapper productMapper;

	@Transactional
	@Override
	public int register(ReplyVO vo) {
		if (vo.getKind() == 'Q') {
			productMapper.updateQuestionReplyCnt(vo.getPno(), 1);
		} else {
			productMapper.updateReviewReplyCnt(vo.getPno(), 1);
		}
		
		return mapper.depthInsert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.get(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {

		ReplyVO vo = mapper.get(rno);
		
		if(vo.getKind()=='Q') {
		productMapper.updateQuestionReplyCnt(vo.getPno(), -1);
		}else {
		productMapper.updateReviewReplyCnt(vo.getPno(), -1);
		}
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return mapper.count(map);
	}

	@Override
	public ReplyPageDTO getListPage(Map<String, Object> map) {
		return new ReplyPageDTO(mapper.count(map), 
								mapper.parentCount(map), 
								mapper.getList(map),
								mapper.scoreAverage(map));
	}
	
	@Override
	public List<ReplyVO> getDetailList(Map<String, Object> map) {
		return mapper.depthGetList(map);
	}

	@Override
	public int myOrderCount(Map<String, Object> map) {
		return mapper.myOrderCount(map);
	}

}

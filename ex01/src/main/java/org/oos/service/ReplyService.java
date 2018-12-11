package org.oos.service;

import java.util.List;
import java.util.Map;

import org.oos.domain.ReplyPageDTO;
import org.oos.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);
	public ReplyVO get(Long rno);
	public int modify(ReplyVO vo);
	public int remove(Long rno);
	public List<ReplyVO> getList(Map<String, Object> map);	
	public List<ReplyVO> getDetailList(Map<String, Object> map);
	public ReplyPageDTO getListPage(Map<String, Object> map);
	public int count(Map<String, Object> map);
	public int myOrderCount(Map<String, Object> map);
}

package org.oos.mapper;

import java.util.List;
import java.util.Map;

import org.oos.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO vo);
	public ReplyVO get(Long rno);
	public int update(ReplyVO vo);
	public int delete(Long rno);
	public List<ReplyVO> getList(Map<String, Object> map);
	public List<ReplyVO> depthGetList(Map<String, Object> map);
	public int depthInsert(ReplyVO vo);
	public int myOrderCount(Map<String, Object> map);
	public int count(Map<String, Object> map);
	public int parentCount(Map<String, Object> map);
	public double scoreAverage(Map<String, Object> map);
	
	
}

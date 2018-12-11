package org.oos.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ReplyPageDTO {
	
	private int replyCnt;
	private int parentCnt;
	private List<ReplyVO> list;
	private double scoreAverage;
}

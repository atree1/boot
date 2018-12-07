package org.oos.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductVO {

	private Long pno;
	private String pname;
	private Long price;
	private Long sno;
	private Date regdate;
	private Date updatedate;
	private List<ProductImgVO> imgList;
	private List<ProductOptionVO> optList;
	private  char del;	
	private int QuestionReplyCnt;
	private int ReviewReplyCnt;
}

package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class StoreVO {
	private Long sno;
	private String sname;
	private String owner;
	private String sinfo;
	private String saddress;
	private String building;
	private String floor;
	private String room;
	private String tel;
	private Date regdate;
	private Date updatedate;
	private char del;
	
	private ProductImgVO img;
}

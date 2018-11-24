package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SellerVO {

	private String sid;
	private String spw;
	private String sname;
	private String bnum;
	private String pnum;
	private String tel;
	private Date regdate;
	private Date updatedate;
	private  char del;
}

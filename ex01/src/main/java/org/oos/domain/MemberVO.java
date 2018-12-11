package org.oos.domain;

import lombok.Data;

@Data
public class MemberVO {

	private String mid;
	private String mpw;
	private String mname;
	private String pnum;
	private String birth;
	private String email;
	private String address;
	private String addressDetail;
	private char permit;
	private  char del;
}

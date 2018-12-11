package org.oos.domain;

import lombok.Data;

@Data
public class OrderDetailVO {

	private Long odno;
	private Long ono;
	private Long pno;
	private Long qty;
	private Long opno;
	private String pnum;
	private String address;
	private String addressDetail;
	private String del;
	private String mid;
	private Long sno;
	
	private ProductVO product;
	private ProductOptionVO option;
}

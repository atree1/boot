package org.oos.domain;

import lombok.Data;

@Data
public class OrderDetailVO {

	private Long odno;
	private String mid;
	private Long sno;
	private Long ono;
	private Long pno;
	private Long qty;
	private Long opno;
	private String del;
	private ProductVO product;
	private ProductOptionVO option;
}

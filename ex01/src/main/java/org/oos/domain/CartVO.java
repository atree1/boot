package org.oos.domain;

import lombok.Data;

@Data
public class CartVO {

	private Long cno;
	private String mid;
	private Long pno;
	private Long qty;
	private Long opt;
}
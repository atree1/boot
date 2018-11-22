package org.oos.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderVO {

	private Long ono;
	private String mid;
	private String state;
	private Date regdate;
	
}

package org.oos.domain;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	private int pageNum;
	private int amount;
	
	private String dayStart;
	private String dayEnd;
	private String category;
	private String keyword;
	private String orderby;

	public Criteria() {
		this.pageNum=1;
		this.amount=12;
	}

	public Criteria(int pageNum, int amount) {
		
		this.pageNum=pageNum;
		this.amount=amount;
	}
	
	public int getSkip() {
		
		return ((this.pageNum - 1) * amount);

	}


}

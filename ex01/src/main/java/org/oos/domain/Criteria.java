package org.oos.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	private int pageNum;
	private int amount;
	
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		// TODO Auto-generated constructor stub
		
		this.pageNum=pageNum;
		this.amount=amount;
		
	}

	public int getSkip() {
		
		return (this.pageNum - 1) * amount;

	}

}

package com.kimjaeeun.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data @AllArgsConstructor 
public class ReplyCriteria extends Criteria{
	private Long lastRno=0L;

	public ReplyCriteria(){
		this(10);
	}
	public ReplyCriteria(int amount){
		setAmount(amount);
	}
	public ReplyCriteria(Long lastRno, int amount) {
		this(lastRno);
		setAmount(amount);
	}
	
	
}

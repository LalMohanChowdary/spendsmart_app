package com.example.spendsmart.incomeexceptionclass;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoIncomeFound extends RuntimeException {
	
	private String message;

	public NoIncomeFound(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	

}

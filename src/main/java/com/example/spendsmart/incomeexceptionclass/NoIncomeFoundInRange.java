package com.example.spendsmart.incomeexceptionclass;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class NoIncomeFoundInRange extends RuntimeException {

	private String message;

	public NoIncomeFoundInRange(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}

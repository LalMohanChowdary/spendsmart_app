package com.example.spendsmart.expensesexceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoExpensesFoundInRange extends RuntimeException {

	private String message;

	public NoExpensesFoundInRange(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}

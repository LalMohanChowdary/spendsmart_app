package com.example.spendsmart.expensesexceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoExpensesFound extends RuntimeException {

	private String message;

	public NoExpensesFound(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}

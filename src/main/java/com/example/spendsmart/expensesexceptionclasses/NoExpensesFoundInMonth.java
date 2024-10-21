package com.example.spendsmart.expensesexceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoExpensesFoundInMonth extends RuntimeException{

	private String message;

	public NoExpensesFoundInMonth(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return this.message;
	}
}

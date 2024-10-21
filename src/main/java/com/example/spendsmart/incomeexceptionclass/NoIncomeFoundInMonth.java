package com.example.spendsmart.incomeexceptionclass;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoIncomeFoundInMonth extends RuntimeException {
	private String message;

	public NoIncomeFoundInMonth(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}

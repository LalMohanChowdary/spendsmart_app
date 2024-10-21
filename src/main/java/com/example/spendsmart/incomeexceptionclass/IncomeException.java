package com.example.spendsmart.incomeexceptionclass;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class IncomeException extends RuntimeException {
	private String message;

	public IncomeException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}

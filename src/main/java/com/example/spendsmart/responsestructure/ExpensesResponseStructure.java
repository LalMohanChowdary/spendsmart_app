package com.example.spendsmart.responsestructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExpensesResponseStructure<E> {

	private int status;
	private String message;
	private E body;
}

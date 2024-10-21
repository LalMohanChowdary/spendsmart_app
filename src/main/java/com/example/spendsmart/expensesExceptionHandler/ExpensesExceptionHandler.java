package com.example.spendsmart.expensesExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spendsmart.expensesexceptionclasses.NoExpensesFound;
import com.example.spendsmart.expensesexceptionclasses.NoExpensesFoundInMonth;
import com.example.spendsmart.expensesexceptionclasses.NoExpensesFoundInRange;
import com.example.spendsmart.responsestructure.ExpensesResponseStructure;

@RestControllerAdvice
public class ExpensesExceptionHandler {
	
	@ExceptionHandler(NoExpensesFound.class)
	public ResponseEntity<?> noExpensesFound(NoExpensesFound e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ExpensesResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.body("No Expenses Found").message("No Expenses Found")
				.build());
				
	}
	@ExceptionHandler(NoExpensesFoundInMonth.class)
	public ResponseEntity<?> noExpensesFoundInMonth(NoExpensesFoundInMonth e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ExpensesResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.body("No Expenses Found In Month").message("No Expenses Found")
				.build());
				
	}
	@ExceptionHandler(NoExpensesFoundInRange.class)
	public ResponseEntity<?> noExpensesFoundInRange(NoExpensesFoundInRange e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ExpensesResponseStructure.builder()
						.status(HttpStatus.NOT_FOUND.value())
						.body("No Expenses Found In Given Range").message("No Expenses Found")
						.build());
		
	}

}

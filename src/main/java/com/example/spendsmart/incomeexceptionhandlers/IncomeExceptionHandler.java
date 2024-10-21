package com.example.spendsmart.incomeexceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.spendsmart.incomeexceptionclass.IncomeException;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFound;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFoundInMonth;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFoundInRange;
import com.example.spendsmart.responsestructure.IncomeResponseStructure;

@RestControllerAdvice
public class IncomeExceptionHandler {

	@ExceptionHandler(NoIncomeFoundInRange.class)
	public ResponseEntity<?> noIncomeFoundInRange(NoIncomeFoundInRange e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(IncomeResponseStructure.builder().status(HttpStatus.NOT_FOUND.value())
						.message("No Income Found In Given Range").body(e.getMessage()).build());
	}

	@ExceptionHandler(NoIncomeFoundInMonth.class)
	public ResponseEntity<?> noIncomeFoundInMonth(NoIncomeFoundInMonth e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(IncomeResponseStructure.builder().status(HttpStatus.NOT_FOUND.value())
						.message("No Income Found In Given Month").body(e.getMessage()).build());
	}

	@ExceptionHandler(NoIncomeFound.class)
	public ResponseEntity<?> noIncomeFound(NoIncomeFound e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(IncomeResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No Income Found").body(e.getMessage()).build());
	}

	@ExceptionHandler(IncomeException.class)
	public ResponseEntity<?> incomeException(IncomeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(IncomeResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No Income Found").body(e.getMessage()).build());
	}

}

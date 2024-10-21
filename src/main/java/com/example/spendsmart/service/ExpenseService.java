package com.example.spendsmart.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.spendsmart.entity.Expenses;

@Component
public interface ExpenseService {

	ResponseEntity<?> saveExpensesDetails(Expenses expense);

	ResponseEntity<?> findAllExpenses(String email, String password);

	ResponseEntity<?> findExpenseInRange(double min, double max);

	ResponseEntity<?> calculateTotalExpenses();

	ResponseEntity<?> deleteExpenseById(int id);

	ResponseEntity<?> findExpensesInMonth(int month);

}

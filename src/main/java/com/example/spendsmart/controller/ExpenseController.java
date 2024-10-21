package com.example.spendsmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.spendsmart.entity.Expenses;
import com.example.spendsmart.service.ExpenseService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Operation(summary = "Saves The Expenses", description = "Save The Expenses With Date And SpentOn")
	@PostMapping
	public ResponseEntity<?> saveExpensesDetails(@RequestBody Expenses expenses) {
		return expenseService.saveExpensesDetails(expenses);
	}

	@Operation(summary = "Fetch All The Expenses", description = "Fetch All The Expenses With Date And SpentOn If And Only If Credentials Are Matching")
	@GetMapping
	public ResponseEntity<?> findAllExpenses(@RequestParam String email, @RequestParam String password) {
		return expenseService.findAllExpenses(email, password);
	}

	@Operation(summary = "Fetch The Expenses With In Range", description = "Fetch The Expenses Within Range Of Amount")
	@GetMapping("/inrange")
	public ResponseEntity<?> findExpenseInRange(@RequestParam double min, @RequestParam double max) {
		return expenseService.findExpenseInRange(min, max);
	}

	@Operation(summary = "Fetch The Income In a Month", description = "Fetches The Expenses In A Particular Month You Spent")
	@GetMapping("/inmonth")
	public ResponseEntity<?> findExpensesInMonth(@RequestParam int month) {
		return expenseService.findExpensesInMonth(month);
	}

	@Operation(summary = "Fetch The TotalExpenses", description = "Fetch The TotalExpenses You Spent")
	@GetMapping("/totalexpenses")
	public ResponseEntity<?> calculateTotalExpenses() {
		return expenseService.calculateTotalExpenses();
	}

	@Operation(summary = "Delete Expenses By Id", description = "Delete Expenses By Id ")
	@DeleteMapping
	public ResponseEntity<?> deleteExpensesById(@RequestParam int id) {
		return expenseService.deleteExpenseById(id);

	}

}

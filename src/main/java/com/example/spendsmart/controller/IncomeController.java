package com.example.spendsmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spendsmart.entity.Income;
import com.example.spendsmart.service.IncomeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/income")
//@CrossOrigin("http://localhost:3001")
public class IncomeController {

	@Autowired
	private IncomeService incomeService;

	@Operation(summary = "Saves The Income", description = "Save The Income With Date And Source Of Income")
	@PostMapping
	public ResponseEntity<?> saveIncomeDetails(@RequestBody Income income) {
		return incomeService.saveIncomeDetails(income);
	}

	@Operation(summary = "Fetch All The Incomes", description = "Fetch All The Incomes With Date And Source Of Income If And Only If Credientials Are Matching")
	@GetMapping
	public ResponseEntity<?> findAllIncomes(@RequestParam String email, @RequestParam String password) {
		return incomeService.findAllIncomes(email, password);
	}

	@Operation(summary = "Fetch The Income With In Range", description = "Fetch The Income Within Range Of Amount")
	@GetMapping("/inrange")
	public ResponseEntity<?> findIncomeInRange(@RequestParam double min, @RequestParam double max) {
		return incomeService.findIncomeInRange(min, max);
	}

	@Operation(summary = "Fetch The Income In A Particular Month", description = "Fetch The Income Within A Particular Month")
	@GetMapping("inmonth")
	public ResponseEntity<?> findIncomeInMonth(@RequestParam int month) {
		return incomeService.findIncomeInMonth(month);

	}

	@Operation(summary = "Fetch The TotalIncome", description = "Fetches The TotalIncome")
	@GetMapping("/totalincome")
	public ResponseEntity<?> calculateTotalIncome() {
		return incomeService.claculateTotalIncome();

	}

	@Operation(summary = "Deletes The Income", description = "Deletes The Income By Id")
	@DeleteMapping
	public ResponseEntity<?> deleteIncomeById(@RequestParam int id) {
		return incomeService.deleteIncomeById(id);
	}

}

package com.example.spendsmart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.spendsmart.entity.Expenses;
import com.example.spendsmart.entity.Income;

@Component
public interface ExpensesDao {

	Expenses saveExpensesDetails(Expenses expenses);

	List<Expenses> findAllExpenses();

	List<Expenses> findExpensesInRange(double min, double max);

	Optional<Expenses> findExpensesById(int id);
	
	void deleteExpenseById(int id);

	List<Expenses> findExpensesInMonth(int month);
}

package com.example.spendsmart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spendsmart.entity.Expenses;
import com.example.spendsmart.entity.Income;
import com.example.spendsmart.repository.ExpensesRepository;

@Repository
public class ExpensesDaoImpl implements ExpensesDao {
	@Autowired
	private ExpensesRepository expensesRepository;

	@Override
	public Expenses saveExpensesDetails(Expenses expenses) {
		return expensesRepository.save(expenses);
	}

	@Override
	public List<Expenses> findAllExpenses() {
		return expensesRepository.findAll();
	}

	@Override
	public List<Expenses> findExpensesInRange(double min, double max) {
		return expensesRepository.findExpensesInRange(min,max);
	}

	@Override
	public Optional<Expenses> findExpensesById(int id) {
		return expensesRepository.findById(id);
	}

	@Override
	public void deleteExpenseById(int id) {

		 expensesRepository.deleteById(id);
	}

	@Override
	public List<Expenses> findExpensesInMonth(int month) {
		return expensesRepository.findExpensesInMonth(month);
	}
	

}

package com.example.spendsmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spendsmart.entity.Expenses;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {

	@Query("SELECT e FROM Expenses e WHERE e.amount BETWEEN :min AND :max")
	List<Expenses> findExpensesInRange(double min, double max);

	@Query("SELECT e FROM Expenses e WHERE FUNCTION('MONTH', e.date) = :month")
	List<Expenses> findExpensesInMonth(int month);

}

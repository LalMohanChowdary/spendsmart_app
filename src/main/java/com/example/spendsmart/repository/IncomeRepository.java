package com.example.spendsmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spendsmart.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

	@Query("SELECT i FROM Income i WHERE i.amount BETWEEN :min AND :max")
	List<Income> findIncomeInRange(double min, double max);

	@Query("SELECT i FROM Income i WHERE FUNCTION('MONTH', i.date) = :month")
	List<Income> findIncomeInMonth(int month);

}

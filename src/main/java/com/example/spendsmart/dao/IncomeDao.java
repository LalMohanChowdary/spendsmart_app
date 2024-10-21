package com.example.spendsmart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import com.example.spendsmart.entity.Income;

@Component
public interface IncomeDao {

	Income saveIncomeDetails(Income income);

	List<Income> findAllIncomes();

	List<Income> findIncomeInRange(double min, double max);

	List<Income> findIncomeInMonth(int month);

	Optional<Income> findIncomeById(int id);
	
	void deleteIncomeById(int id);

}
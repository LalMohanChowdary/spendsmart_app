package com.example.spendsmart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.spendsmart.entity.Income;
import com.example.spendsmart.repository.IncomeRepository;

@Repository
public class IncomeDaoImpl implements IncomeDao {
	
	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public Income saveIncomeDetails(Income income) {
		return incomeRepository.save(income);
	}

	@Override
	public List<Income> findAllIncomes() {
		return incomeRepository.findAll();
	}

	@Override
	public List<Income> findIncomeInRange(double min, double max) {
		return incomeRepository.findIncomeInRange(min,max);
	}

	@Override
	public List<Income> findIncomeInMonth(int month) {
		return incomeRepository.findIncomeInMonth(month);
	}

	@Override
	public Optional<Income> findIncomeById(int id) {
		return incomeRepository.findById(id);
	}

	@Override
	public void deleteIncomeById(int id) {
		incomeRepository.deleteById(id);
	}


}

package com.example.spendsmart.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.spendsmart.entity.Income;

@Component
public interface IncomeService {

	ResponseEntity<?> saveIncomeDetails(Income income);

	ResponseEntity<?> findAllIncomes(String emial,String password);

	ResponseEntity<?> findIncomeInRange(double min, double max);

	ResponseEntity<?> findIncomeInMonth(int month);

	ResponseEntity<?> claculateTotalIncome();

	ResponseEntity<?> deleteIncomeById(int id);


}

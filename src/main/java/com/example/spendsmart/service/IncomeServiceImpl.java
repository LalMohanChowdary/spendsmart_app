package com.example.spendsmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spendsmart.dao.IncomeDao;
import com.example.spendsmart.entity.Income;
import com.example.spendsmart.entity.User;
import com.example.spendsmart.incomeexceptionclass.IncomeException;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFound;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFoundInMonth;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFoundInRange;
import com.example.spendsmart.repository.IncomeRepository;
import com.example.spendsmart.repository.UserRepository;
import com.example.spendsmart.responsestructure.IncomeResponseStructure;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IncomeDao incomeDao;

	@Override
	public ResponseEntity<?> saveIncomeDetails(Income income) {
		Income dbincome = incomeDao.saveIncomeDetails(income);
		return ResponseEntity.status(HttpStatus.CREATED).body(IncomeResponseStructure.builder()
				.status(HttpStatus.CREATED.value()).message("Income Added Sucessfully").body(dbincome).build());
	}

	@Override
	public ResponseEntity<?> findAllIncomes(String email, String password) {
		if (!isValidUser(email, password)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(IncomeResponseStructure.builder()
					.status(HttpStatus.UNAUTHORIZED.value()).message("Invalid email or password").build());
		}

		List<Income> incomeLi = incomeDao.findAllIncomes();
		if (incomeLi.isEmpty()) {
			throw NoIncomeFound.builder().message("No Income Found ").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(IncomeResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Incomes fetched successfully").body(incomeLi).build());
	}

	private boolean isValidUser(String email, String password) {
		Optional<User> userOpt = userRepository.findByEmail(email);

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			return passwordMatches(password, user.getPassword());
		}
		return false;
	}

	private boolean passwordMatches(String rawPassword, String encodedPassword) {
		return rawPassword.equals(encodedPassword);
	}

	@Override
	public ResponseEntity<?> findIncomeInRange(double min, double max) {

		List<Income> incomeli = incomeDao.findIncomeInRange(min, max);
		if (max > min) {
			if (incomeli.isEmpty()) {
				throw NoIncomeFoundInRange.builder().message("No Incomes Found In Range").build();
			}
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(IncomeResponseStructure.builder().status(HttpStatus.FOUND.value())
							.message("Income InRange Fetched Sucessfully").body(incomeli).build());

		}
		throw IncomeException.builder().message("Min Income Is Greater Than Max Income Please Fix It").build();
	}

	@Override
	public ResponseEntity<?> findIncomeInMonth(int month) {

		List<Income> incomeli = incomeDao.findIncomeInMonth(month);

		if (incomeli.isEmpty()) {
			throw NoIncomeFoundInMonth.builder().message("No Incomes Found In Given Month").build();
		}

		return ResponseEntity.status(HttpStatus.FOUND).body(IncomeResponseStructure.builder()
				.status(HttpStatus.FOUND.value()).message("Income InMonth Fetched Sucessfully").body(incomeli).build());
	}

	@Override
	public ResponseEntity<?> claculateTotalIncome() {

		List<Income> incomeli = incomeDao.findAllIncomes();
		if (incomeli.isEmpty()) {
			throw NoIncomeFound.builder().message("No Income Found").build();
		}

		double totalIncome = 0;
		for (Income in : incomeli) {
			totalIncome = totalIncome + in.getAmount();
		}

		return ResponseEntity.status(HttpStatus.OK).body(IncomeResponseStructure.builder().status(HttpStatus.OK.value())
				.body(totalIncome).message("Total IncomeCalculated Sucessfully").build());
	}

	public double claculateIncome() {

		List<Income> incomeli = incomeDao.findAllIncomes();

		double totalIncome = 0;
		for (Income in : incomeli) {
			totalIncome = totalIncome + in.getAmount();
		}

		return totalIncome;
	}

	@Override
	public ResponseEntity<?> deleteIncomeById(int id) {
		Optional<Income> dbincome = incomeDao.findIncomeById(id);

		if (dbincome.isEmpty()) {
			throw NoIncomeFound.builder().message("No Income Found In DataBase With id "+id).build();
		}
		incomeDao.deleteIncomeById(id);

		return ResponseEntity.status(HttpStatus.OK)
				.body(IncomeResponseStructure.builder()
						.status(HttpStatus.OK.value())
				.body(dbincome)
				.message("Income Deleted With id "+ id)
				.build());
	}

}

package com.example.spendsmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spendsmart.dao.ExpensesDao;
import com.example.spendsmart.entity.Expenses;
import com.example.spendsmart.entity.Income;
import com.example.spendsmart.entity.User;
import com.example.spendsmart.expensesexceptionclasses.NoExpensesFound;
import com.example.spendsmart.expensesexceptionclasses.NoExpensesFoundInMonth;
import com.example.spendsmart.expensesexceptionclasses.NoExpensesFoundInRange;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFound;
import com.example.spendsmart.incomeexceptionclass.NoIncomeFoundInMonth;
import com.example.spendsmart.repository.UserRepository;
import com.example.spendsmart.responsestructure.ExpensesResponseStructure;
import com.example.spendsmart.responsestructure.IncomeResponseStructure;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpensesDao expensesDao;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> saveExpensesDetails(Expenses expenses) {

		Expenses dbexpenses = expensesDao.saveExpensesDetails(expenses);
		return ResponseEntity.status(HttpStatus.CREATED).body(ExpensesResponseStructure.builder()
				.status(HttpStatus.CREATED.value()).message("Expenses Added Sucessfully").body(dbexpenses).build());
	}

	@Override
	public ResponseEntity<?> findAllExpenses(String email, String password) {
		if (!isValidUser(email, password)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(IncomeResponseStructure.builder()
					.status(HttpStatus.UNAUTHORIZED.value()).message("Invalid email or password").build());
		}

		List<Expenses> expensesli = expensesDao.findAllExpenses();
		return ResponseEntity.status(HttpStatus.OK).body(IncomeResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Expenses fetched successfully").body(expensesli).build());
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
	public ResponseEntity<?> findExpenseInRange(double min, double max) {

		List<Expenses> expensesli = expensesDao.findExpensesInRange(min, max);

		if(expensesli.isEmpty()) {
			throw NoExpensesFoundInRange.builder()
			.message("No Expenses Found In Given Range")
			.build();
		}
		return ResponseEntity.status(HttpStatus.FOUND)
						.body(ExpensesResponseStructure.builder()
						.status(HttpStatus.FOUND.value())
						.message("Expenses InRange Fetched Sucessfully")
						.body(expensesli).build());

	}
	
	
	@Override
	public ResponseEntity<?> findExpensesInMonth(int month) {

		List<Expenses> expensesli = expensesDao.findExpensesInMonth(month);

		if (expensesli.isEmpty()) {
			throw NoExpensesFoundInMonth.builder()
			.message("No Expenses Found In Given Month")
			.build();
		}

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(ExpensesResponseStructure.builder()
				.status(HttpStatus.FOUND.value())
				.message("Expenses InMonth Fetched Sucessfully")
				.body(expensesli).build());
	}


	@Override
	public ResponseEntity<?> calculateTotalExpenses() {

		List<Expenses> expensesli = expensesDao.findAllExpenses();

		double totalExpenses = 0;

		for (Expenses ex : expensesli) {
			totalExpenses += ex.getAmount();
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(ExpensesResponseStructure.builder()
				.status(HttpStatus.OK.value())
				.body(totalExpenses)
				.message("Expenses Calculated Sucessfully")
				.build());
	}

	public double claculateExpenses() {
		List<Expenses> expensesli = expensesDao.findAllExpenses();
		double totalExpenses = 0;
		for (Expenses ex : expensesli) {
			totalExpenses += ex.getAmount();
		}
		return totalExpenses;
	}

	@Override
	public ResponseEntity<?> deleteExpenseById(int id) {

		Optional<Expenses> dbexp = expensesDao.findExpensesById(id);

		if (dbexp.isEmpty()) {
			throw NoExpensesFound
			.builder()
			.message("No Expenses Found For ID " + id)
			.build();
		}
		expensesDao.deleteExpenseById(id);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ExpensesResponseStructure
				.builder()
				.status(HttpStatus.OK.value())
				.message("Expenses Deleted With ID " + id).body(dbexp)
				.build());
	}
}

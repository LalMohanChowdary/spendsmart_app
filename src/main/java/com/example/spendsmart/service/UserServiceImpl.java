package com.example.spendsmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spendsmart.dao.UserDao;
import com.example.spendsmart.entity.User;
import com.example.spendsmart.responsestructure.IncomeResponseStructure;
import com.example.spendsmart.responsestructure.UserResponseStructure;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	ExpenseServiceImpl expenseimpl;
	
	@Autowired
	IncomeServiceImpl serviceImpl;

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<?> saveUserDetails(User user) {
		user = userDao.saveUserDetails(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseStructure.builder()
				.status(HttpStatus.CREATED.value()).message("User Created Sucessfully").body(user).build());
	}

	@Override
	public ResponseEntity<?> getCurrentBalance() {

		double totalIncome = serviceImpl.claculateIncome();
	   
		double totalExpenses = expenseimpl.claculateExpenses();
		
		double currentBalance = totalIncome - totalExpenses;

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(UserResponseStructure.builder().status(HttpStatus.OK.value()).body(currentBalance)
						.message("Your Current Balance Fetched Sucessfully").build());
	}

}

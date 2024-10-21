
package com.example.spendsmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spendsmart.entity.User;
import com.example.spendsmart.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
//@CrossOrigin("http://localhost:3001")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "To Save The Users", description = "This API Will Save The User Into The DataBase")
	@PostMapping
	public ResponseEntity<?> saveUserDetails(@RequestBody User user) {

		return userService.saveUserDetails(user);
	}

	@Operation(summary = "To Fetch The Current Balance Of the User", description = "This API Will Fech The CurrentBalance After All The Expenses Removed From The Income ")
	@GetMapping("/currentbalance")
	public ResponseEntity<?> getCurrentBalance() {
		return userService.getCurrentBalance();
	}
}

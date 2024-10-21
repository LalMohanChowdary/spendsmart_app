package com.example.spendsmart.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.spendsmart.entity.User;

@Component
public interface UserService {

	ResponseEntity<?> saveUserDetails(User user);

	ResponseEntity<?> getCurrentBalance();

}

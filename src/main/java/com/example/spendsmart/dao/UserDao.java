package com.example.spendsmart.dao;

import org.springframework.stereotype.Component;

import com.example.spendsmart.entity.User;

@Component
public interface UserDao {

	User saveUserDetails(User user);

}

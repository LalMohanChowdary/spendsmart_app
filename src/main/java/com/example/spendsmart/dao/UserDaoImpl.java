package com.example.spendsmart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spendsmart.entity.User;
import com.example.spendsmart.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUserDetails(User user) {
		return userRepository.save(user);
	}

}

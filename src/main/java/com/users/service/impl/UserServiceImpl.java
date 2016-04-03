package com.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.dao.UserRepository;
import com.users.model.User;
import com.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	@Transactional
	public User insert(User u1) {
		return userRepository.save(u1);
	}

}

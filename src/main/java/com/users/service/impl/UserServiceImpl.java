package com.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.dao.UserRepository;
import com.users.dao.UserRoleRepository;
import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	// insert in users table
	@Override
	@Transactional
	public User insert(User u1) {
		return userRepository.save(u1);
	}

	// insert in userrole table
	@Override
	@Transactional
	public UserRole insert2(UserRole u2) {

		return userRoleRepository.save(u2);
	}

	// delete user
	@Override
	@Transactional
	public void deleteUser(User u) {
		userRepository.delete(u);
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional
	public List<User> findAll() {

		return userRepository.findAll();
	}

}

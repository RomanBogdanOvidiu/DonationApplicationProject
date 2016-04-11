package com.users.service;

import java.util.List;

import com.users.model.User;
import com.users.model.UserRole;

public interface UserService {

	User insert(User u1);

	UserRole insert2(UserRole u2);

	void deleteUser(User u);

	User findByUsername(String username);

	List<User> findAll();
}

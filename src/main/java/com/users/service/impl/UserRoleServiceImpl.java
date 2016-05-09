package com.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.model.User;
import com.users.model.UserRole;
import com.users.repository.UserRoleRepository;
import com.users.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	@Transactional
	public UserRole findByUser(User u) {

		return userRoleRepository.findByUser(u);
	}

	@Override
	@Transactional
	public UserRole save(UserRole us) {

		return userRoleRepository.save(us);
	}

}

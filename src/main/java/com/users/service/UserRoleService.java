package com.users.service;

import com.users.model.User;
import com.users.model.UserRole;

public interface UserRoleService {
	UserRole save(UserRole us);
	UserRole findByUser(User u);

}

package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.User;
import com.users.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	UserRole findByUser(User user);
}

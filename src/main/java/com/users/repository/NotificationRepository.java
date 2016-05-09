package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.Notification;
import com.users.model.Patient;

@Repository
public interface NotificationRepository extends JpaRepository<Notification , Integer>{
	Notification findById(Integer id);
	
}

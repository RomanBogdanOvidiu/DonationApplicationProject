package com.users.service;

import java.util.List;

import com.users.model.Notification;

public interface NotificationService {
	Notification save(Notification r);

	List<Notification> findAll();

	void deleteById(Integer id);
	
	Notification findById(Integer id);
}

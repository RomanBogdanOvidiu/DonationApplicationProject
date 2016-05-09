package com.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.model.Notification;
import com.users.model.Patient;
import com.users.repository.NotificationRepository;
import com.users.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public Notification save(Notification r) {

		return notificationRepository.save(r);
	}

	@Override
	@Transactional
	public List<Notification> findAll() {

		return notificationRepository.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		notificationRepository.delete(id);

	}

	@Override
	public Notification findById(Integer id) {
		return notificationRepository.findById(id);
	}

}

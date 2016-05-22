package com.users.service;

import java.util.List;

import com.users.model.Donation;
import com.users.model.User;

public interface DonationService {

	Donation insert(Donation d1);
	List<Donation> findAll();
	List<Donation> findByUser(User u);
	Donation findByDonationsId(Integer id);
	void delete(Donation don);
}

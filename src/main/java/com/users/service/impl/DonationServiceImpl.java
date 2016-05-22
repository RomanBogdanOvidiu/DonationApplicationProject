package com.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.model.Donation;
import com.users.model.User;
import com.users.repository.DonationRepository;
import com.users.service.DonationService;

@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	DonationRepository donationRepository;

	public Donation insert(Donation d1) {
		return donationRepository.save(d1);
	}

	@Override
	public List<Donation> findAll() {
		return donationRepository.findAll();
	}

	@Override
	public List<Donation> findByUser(User u) {

		return donationRepository.findByUser(u);
	}

	@Override
	public Donation findByDonationsId(Integer id) {
		return donationRepository.findByDonationsId(id);
	}

	@Override
	public void delete(Donation don) {
		donationRepository.delete(don);
	}

}

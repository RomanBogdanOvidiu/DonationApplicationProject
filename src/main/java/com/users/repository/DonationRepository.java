package com.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.Donation;
import com.users.model.User;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

	List<Donation> findByUser(User u);
	Donation findByDonationsId(Integer id);
}

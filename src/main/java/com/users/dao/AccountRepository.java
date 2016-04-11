package com.users.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.Account;
import com.users.model.Client;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	List<Account> findByClient(Client client);

	Account findByAccountNo(String id);

	Account findById(Integer id);
}

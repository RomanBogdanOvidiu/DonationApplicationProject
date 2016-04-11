package com.users.service;

import java.util.List;

import com.users.model.Account;
import com.users.model.Client;


public interface AccountService {

	Account insert(Account a1);
	List<Account> findByClient(Client client);
	
	void deleteById(Integer id);
	void delete(Account account);
	Account findById(Integer id);
	Account findByAccountNo(String id);
}

package com.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.dao.AccountRepository;
import com.users.model.Account;
import com.users.model.Client;
import com.users.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;
	
	//INSERT IN ACCOUNT TABLE
		@Override
		@Transactional
		public Account insert(Account a1){
			return accountRepository.save(a1);
		}
		
		@Override
		@Transactional
		public List<Account> findByClient(Client client) {
				return accountRepository.findByClient(client);
		       
		} 
		
		@Override
		@Transactional
		public void deleteById(Integer id){
			 accountRepository.delete(id);
		}
}

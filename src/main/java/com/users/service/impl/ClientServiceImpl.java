package com.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.dao.ClientRepository;
import com.users.service.ClientService;

import com.users.model.Client;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional
	public Client insert(Client c1) {
		return clientRepository.save(c1);
	}

	@Override
	@Transactional
	public Client findByCnp(String CNP) {
		return clientRepository.findByCnp(CNP);
	}

	@Override
	@Transactional
	public List<Client> findAll() {
		
		return clientRepository.findAll();
	}

	@Override
	@Transactional
	public Client findById(Integer id) {
		  Client person = clientRepository.findOne(id);
	        return person;
	}

	@Override
	public void deleteById(Integer id) {
			 clientRepository.delete(id);
		
	}

}

package com.users.service;

import java.util.List;

import com.users.model.Client;

public interface ClientService {

	Client insert(Client c1);

	Client findByCnp(String CNP);

	List<Client> findAll();

	Client findById(Integer id);

	void deleteById(Integer id);

}

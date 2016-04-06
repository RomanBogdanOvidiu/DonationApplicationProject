package com.users.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client , Integer>{

	Client findByCnp(String cnp);
	Client findById(Integer id);

}

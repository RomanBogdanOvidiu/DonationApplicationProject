package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient , Integer>{

	Patient findByCnp(String cnp);
	Patient findById(Integer id);

}

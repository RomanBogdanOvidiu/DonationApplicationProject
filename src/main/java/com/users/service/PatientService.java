package com.users.service;

import java.util.List;

import com.users.model.Patient;

public interface PatientService {

	Patient insert(Patient c1);

	Patient findByCnp(String CNP);

	List<Patient> findAll();

	Patient findById(Integer id);

	void deleteById(Integer id);

}

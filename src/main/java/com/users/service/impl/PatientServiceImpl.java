package com.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.service.PatientService;

import com.users.model.Patient;
import com.users.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	@Transactional
	public Patient insert(Patient c1) {
		return patientRepository.save(c1);
	}

	@Override
	@Transactional
	public Patient findByCnp(String CNP) {
		return patientRepository.findByCnp(CNP);
	}

	@Override
	@Transactional
	public List<Patient> findAll() {
		
		return patientRepository.findAll();
	}

	@Override
	@Transactional
	public Patient findById(Integer id) {
		  Patient person = patientRepository.findOne(id);
	        return person;
	}

	@Override
	public void deleteById(Integer id) {
			 patientRepository.delete(id);
		
	}

}

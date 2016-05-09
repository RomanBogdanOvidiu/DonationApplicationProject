package com.users.service;

import java.util.List;

import com.users.model.Consultation;
import com.users.model.Patient;


public interface ConsultationService {

	Consultation insert(Consultation a1);
	List<Consultation> findByClient(Patient client);
	
	void deleteById(Integer id);
	void delete(Consultation cons);
	Consultation findById(Integer id);
	Consultation findByConsultationNo(String id);
}

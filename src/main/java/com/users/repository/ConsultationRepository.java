package com.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.Consultation;
import com.users.model.Patient;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

	List<Consultation> findByClient(Patient client);

	Consultation findByConsultationNo(String id);

	Consultation findById(Integer id);
}

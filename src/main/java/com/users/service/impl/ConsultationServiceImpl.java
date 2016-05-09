package com.users.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.model.Consultation;
import com.users.model.Patient;
import com.users.repository.ConsultationRepository;
import com.users.service.ConsultationService;

@Service
public class ConsultationServiceImpl implements ConsultationService{
	@Autowired
	private ConsultationRepository consultationRepository;
	
	//INSERT IN consultation TABLE
		@Override
		@Transactional
		public Consultation insert(Consultation a1){
			return consultationRepository.save(a1);
		}
		
		@Override
		@Transactional
		public List<Consultation> findByClient(Patient client) {
				return consultationRepository.findByClient(client);
		       
		} 
		
		@Override
		@Transactional
		public void deleteById(Integer id){
			 consultationRepository.delete(id);
		}
		
		@Override
		@Transactional
		public Consultation findById(Integer id)
		{
			return consultationRepository.findById(id);
		}
		@Override
		@Transactional
		public void delete(Consultation cons){
			 consultationRepository.delete(cons);
		}

		@Override
		public Consultation findByConsultationNo(String id) {
		
			return consultationRepository.findByConsultationNo(id);
		}
}

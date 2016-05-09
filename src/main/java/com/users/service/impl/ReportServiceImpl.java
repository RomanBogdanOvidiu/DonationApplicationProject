package com.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.model.Report;
import com.users.repository.ReportRepository;
import com.users.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportRepository reportRepository;
	@Override
	public Report save(Report r) {
		return reportRepository.save(r);
	}

}

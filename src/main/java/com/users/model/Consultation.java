package com.users.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Entity
@Table(name = "consultation", catalog = "hospital")
public class Consultation {

	@Id
	@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 45)
	private int id;

	private String consultationNo = UUID.randomUUID().toString().replaceAll("-", "");;

	private String consultationDetails;
	@ManyToOne
	@JoinColumn(name = "clientid")
	private Patient client;

	private String programare;

	private boolean checked=false;

	public Consultation() {
	}

	public Consultation(String amout, int id) {
		this.id = id;
		this.consultationDetails = amout;

	}

	public String getProgramare() {
		return programare;
	}

	public void setProgramare(String programare) {
		this.programare = programare;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getClient() {
		return client;
	}

	public void setClient(Patient client) {
		this.client = client;
	}

	@Column(name = "ConsultationNumber", unique = true, nullable = false, length = 10)
	public String getConsultationNo() {
		return consultationNo;
	}

	public void setConsultationNo(String consultationNo) {
		this.consultationNo = consultationNo;
	}

	@Column(name = "ConsultationDetails", unique = false, nullable = false, length = 45)
	public String getConsultationDetails() {
		return consultationDetails;
	}

	public void setConsultationDetails(String consultationDetails) {
		this.consultationDetails = consultationDetails;
	}

}

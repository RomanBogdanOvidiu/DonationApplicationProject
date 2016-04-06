package com.users.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.users.model.Client;

@Entity
@Table(name = "account", catalog = "bank")
public class Account {

	@Id
	@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 45)
	private int id;

	private double amount;
	@ManyToOne
	@JoinColumn(name = "clientid")
	private Client client;
	
	private int date;


	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	
	public Account() {
	}

	public Account( double amout, int id) {
		this.id = id;
		this.amount = amout;

	}
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Amount", unique = false, nullable = false, length = 45)
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void withdraw(double withdrawAmount) {
		assert amount > 0 : "Out of money";
		amount -= withdrawAmount ;
	}

	public void deposit(double depositAmount) {
		amount += depositAmount;

	}
	
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void trasfer(Account c, double amt) {
		if (amt > c.amount) {
			System.out.println("insufficient amount of money");
		} else {
			withdraw(amt);
			c.deposit(amt);
		}

	}

}

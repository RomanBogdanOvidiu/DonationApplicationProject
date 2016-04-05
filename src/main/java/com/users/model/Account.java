package com.users.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.users.model.Client;

@Entity
@Table(name = "account", catalog = "bank")
public class Account {

	@Id
	@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 45)
	private int ID;

	private double amount;
	private double lastAmountOfInterestEarned = 30.0;
	private String password;
	@ManyToOne
	@JoinColumn(name = "Client_ID")
	private Client client;
	private Date date;


	public Account() {
	}

	public Account( double amout, int id) {
		this.ID = id;
		this.amount = amout;

	}
	

	@Column(name = "password", unique = true, nullable = false, length = 45)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
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
		amount -= withdrawAmount + lastAmountOfInterestEarned;
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

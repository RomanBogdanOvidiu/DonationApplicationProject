package com.users.model;
import javax.persistence.Entity;
import javax.persistence.Id;



//@Entity
public class Account {

	@Id
	private int id;

	private User user;
	private String CNP;
	private double amount;
	private double lastAmountOfInterestEarned = 30.0;
	

	public Account (){}
	
	public Account(User Username,double amout,int id){
		this.id = id;
		this.user=Username;
		this.amount=amout;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

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
	
	public void trasfer(Account c, double amt) {
		if (amt > c.amount) {
			System.out.println("insufficient amount of money");
		} else {
			withdraw(amt);
			c.deposit(amt);
		}

	}
	
}

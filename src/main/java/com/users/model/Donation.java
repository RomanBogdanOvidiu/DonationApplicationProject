package com.users.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "donations", catalog = "donation", uniqueConstraints = @UniqueConstraint(columnNames = { "donationsTitle",
		"username" }))
public class Donation {

	private int donationsId;
	private String donationsTitle;
	private String donationsDesc;
	private String city;
	private User user;

	public Donation() {
	}

	public Donation(User user, String donations_title) {
		this.user = user;
		this.donationsTitle = donations_title;
	}

	@Column(name = "City", nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "donationsTitle", nullable = false)
	public String getDonationsTitle() {
		return donationsTitle;
	}

	public void setDonationsTitle(String donationsTitle) {
		this.donationsTitle = donationsTitle;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "donations_id", unique = true, nullable = false)
	public int getDonationsId() {
		return donationsId;
	}

	public void setDonationsId(int donationsId) {
		this.donationsId = donationsId;
	}

	@Column(name = "donations_description", nullable = false)
	public String getDonationsDesc() {
		return donationsDesc;
	}

	public void setDonationsDesc(String donationsDesc) {
		this.donationsDesc = donationsDesc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	public User getUser() {
		return this.user;
	}

	

	public void setUser(User user) {
		this.user = user;
	}

}

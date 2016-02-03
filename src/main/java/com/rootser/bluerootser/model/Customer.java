package com.rootser.bluerootser.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CustomerPK id;

	private Timestamp dob;

	private String email;

	private String firstname;

	private String lastname;

	public Customer() {
	}

	public CustomerPK getId() {
		return this.id;
	}

	public void setId(CustomerPK id) {
		this.id = id;
	}

	public Timestamp getDob() {
		return this.dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", dob=" + dob + ", email=" + email + ", firstname=" + firstname + ", lastname="
				+ lastname + "]";
	}

}
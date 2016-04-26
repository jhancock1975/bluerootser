/**
 * class used for retrieving username
 * with no password
 */
package com.rootser.bluerootser.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User {
	@Id
	@Column(name="username")
	String userName;
	
	@Column(name="firstname")
	String firstName;
	
	@Column(name="lastname")
	String lastName;
	
	@Column(name="enabled")
	Boolean enabled;
	
	@Column(name="email")
	String email;
	
	@Column(name="dob")
	Date dob;
	
	@Transient
	String newPassword;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+  ", enabled=" + enabled + ", email=" + email + "]";
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}

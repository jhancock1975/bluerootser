package com.rootser.bluerootser.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the customers database table.
 * 
 */
@Embeddable
public class CustomerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String userid;

	private String password;

	public CustomerPK() {
	}
	public String getUserid() {
		return this.userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CustomerPK)) {
			return false;
		}
		CustomerPK castOther = (CustomerPK)other;
		return 
			this.userid.equals(castOther.userid)
			&& this.password.equals(castOther.password);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userid.hashCode();
		hash = hash * prime + this.password.hashCode();
		
		return hash;
	}
}
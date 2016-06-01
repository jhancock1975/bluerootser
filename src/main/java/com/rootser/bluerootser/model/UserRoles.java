package com.rootser.bluerootser.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRoles {
	
	public UserRoles(String userName, String role){
		this.username = userName;
		this.role = role;
	}
	
	public UserRoles(Integer id, Date updated, Date created, String username, String role) {
		super();
		this.id = id;
		this.updated = updated;
		this.created = created;
		this.username = username;
		this.role = role;
	}

	@Id
	@Column(name="id")
	Integer id;
	
	@Column(name="updated")
	Date updated;
	
	@Column (name="created")
	Date created;
	
	@Column (name="username")
	String username;
	
	@Column (name="role")
	String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

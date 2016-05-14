package com.rootser.bluerootser.model;

public class UpdateUserResult {
	
	public UpdateUserResult(String updateMsg, User user) {
		super();
		this.updateMsg = updateMsg;
		this.user = user;
	}
	String updateMsg;
	User user;
	public String getUpdateMsg() {
		return updateMsg;
	}
	public void setUpdateMsg(String updateMsg) {
		this.updateMsg = updateMsg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

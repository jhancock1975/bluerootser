package com.rootser.bluerootser.model;

public class UpdateUserResult {
	
	public UpdateUserResult(String updateMsg, User user, boolean updateStatus) {
		super();
		this.updateMsg = updateMsg;
		this.user = user;
		this.updateStatus = updateStatus;
	}
	
	String updateMsg;
	User user;
	boolean updateStatus;
	
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
	public boolean isUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(boolean updateStatus) {
		this.updateStatus = updateStatus;
	}
	
}

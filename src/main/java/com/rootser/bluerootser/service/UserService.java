package com.rootser.bluerootser.service;

import java.util.List;

import com.rootser.bluerootser.model.User;

public interface UserService {
	public List<User> getUser(String userId);

	public User updateUser(User userObj);
}

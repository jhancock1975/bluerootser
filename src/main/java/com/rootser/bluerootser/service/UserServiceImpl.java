package com.rootser.bluerootser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.model.User;
import com.rootser.bluerootser.model.UserWithPassword;
import com.rootser.bluerootser.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepo;
	public List<User> getUser(String userId){
		return userRepo.findByUserName(userId);
	}
	/**
	 * @param userObj - user object coming from browser
	 * handles updating user data
	 * if user with password object has a password
	 * that matches password in user object, we update to
	 * the new password, otherwise we reject
	 * 
	 */
	@Override
	public User updateUser(User userObj) {
		//UserWithPassword userPasswd = user
		userRepo.save(userObj);
		return userObj;
	}
}

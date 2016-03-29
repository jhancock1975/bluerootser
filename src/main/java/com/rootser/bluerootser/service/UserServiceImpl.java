package com.rootser.bluerootser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.model.User;
import com.rootser.bluerootser.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepo;
	public List<User> getUser(String userId){
		return userRepo.findByUserName(userId);
	}
}

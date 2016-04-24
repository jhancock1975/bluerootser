package com.rootser.bluerootser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rootser.bluerootser.model.User;
import com.rootser.bluerootser.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/user") 
	public User getUser(@RequestParam(value="curUserId") String userName){
		return userService.getUser(userName).get(0);
	}
	
	@RequestMapping("/updateUser") 
	public User updateUser(@RequestBody User userObj){
		return userService.updateUser(userObj);
	}
}

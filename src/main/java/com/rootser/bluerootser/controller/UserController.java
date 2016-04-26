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
	/**
	 * this method gets called when the user clicks "my area," link at
	 * the top of the nav bar.
	 * 
	 * the user object that it returns does not contain the password
	 * @param userName
	 * @return
	 */
	@RequestMapping("/user") 
	public User getUser(@RequestParam(value="curUserId") String userName){
		return userService.getUser(userName).get(0);
	}
	
	/**
	 * handles form data that is sent when user clicks update
	 * button on the, "my area," form
	 * 
	 * the userObj may or may not contain password data
	 * whether or not to update the password is delegated
	 * to the userService
	 * 
	 * @param userObj
	 * @return
	 */
	@RequestMapping("/updateUser") 
	public User updateUser(@RequestBody User userObj){
		return userService.updateUser(userObj);
	}
}

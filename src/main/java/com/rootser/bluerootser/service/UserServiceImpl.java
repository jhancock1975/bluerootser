package com.rootser.bluerootser.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.model.UpdateUserResult;
import com.rootser.bluerootser.model.User;
import com.rootser.bluerootser.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepo;
	public List<User> getUser(String userId){
		return userRepo.findByUserName(userId);
	}
	
	boolean meetsCriteria(String password){
		return StringUtils.isNotBlank(password) && password.length() > 8;
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
	public UpdateUserResult updateUser(User updateUser) {
		String updateUserName = updateUser.getUserName();
		if (StringUtils.isNotBlank(updateUserName)){
			List<User> existingUserList = userRepo.findByUserName(updateUserName);
			if (existingUserList != null && existingUserList.size() == 1){
				User existingUser = existingUserList.get(0);
				String existingPassword = existingUser.getPassword();
				if (StringUtils.isNotBlank(existingPassword) && existingPassword.equals(updateUser.getPassword())
						&& existingUser.getUserName().equals(updateUserName)){
					String updatePassword = updateUser.getPassword();
					if (meetsCriteria(updatePassword)){
						existingUser.setPassword(updatePassword);
					}
					Date updateDob = updateUser.getDob(); 
					if (updateDob != null){
						existingUser.setDob(updateUser.getDob());
					}
					String updateEmail = updateUser.getEmail();
					if (StringUtils.isNotBlank(updateEmail)){
						existingUser.setEmail(updateEmail);
					}
					String updateFName = updateUser.getFirstName();
					if (StringUtils.isNotBlank(updateFName)){
						existingUser.setFirstName(updateFName);
					}
					String updateLName = updateUser.getLastName();
					if (StringUtils.isNotBlank(updateLName)){
						existingUser.setLastName(updateLName);
					}
					userRepo.save(existingUser);
					return new UpdateUserResult("Your account is updated successfully.", updateUser);
				} else{
					//passwords or user name don't match
					return new UpdateUserResult("Your account is not updated.  You gave invalid security credentials.", updateUser);
				}
					
			} else {
				//zero, two or more users returned from search
				return new UpdateUserResult("Your account is not updated due to a system error.", updateUser);
			}
			
		} else {
			//user name is blank
			return new UpdateUserResult("Your account is not updated.  You gave invalid security credentials.", updateUser);
		}
	}
}

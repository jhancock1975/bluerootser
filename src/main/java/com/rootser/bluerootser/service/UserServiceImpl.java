package com.rootser.bluerootser.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
		return StringUtils.isNotBlank(password) && password.length() > 7;
	}

	/**
	 * returns a success message, or a user-friendly message
	 * about what went wrong when trying to update the account
	 * 
	 * @param updateUser
	 * @return
	 */
	Pair<String,Boolean> validate(User updateUser){


		String updateUserName = updateUser.getUserName();
		if (StringUtils.isBlank(updateUserName)){
			return new ImmutablePair<String, Boolean>("Server side error: no user name present for account update.", false); 
		}

		List<User> existingUserList = userRepo.findByUserName(updateUserName);
		if (existingUserList == null || existingUserList.size() != 1){
			return new ImmutablePair<String, Boolean>("Your account is not updated due to a system error.", false);
		}

		User existingUser = existingUserList.get(0);

		String existingPassword = existingUser.getPassword();
		if (StringUtils.isBlank(existingPassword) || ! existingPassword.equals(updateUser.getPassword())
				|| ! existingUser.getUserName().equals(updateUserName)){
			return new ImmutablePair<String,Boolean>("Your account is not updated.  You gave invalid security credentials.", false);
		}

		String newPassword = existingUser.getNewPassword();
		if (StringUtils.isNotBlank(newPassword) && ! meetsCriteria(existingUser.getNewPassword())){
			return new ImmutablePair<String,Boolean>("The password you supplied is not secure enough. Please use a password that\n"
					+ "contains at least 8 characters.", false);
		}

		return new ImmutablePair<String, Boolean>("", true);

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
		Pair<String,Boolean> validationResult = validate(updateUser);
		if (! validationResult.getRight()){
			return new UpdateUserResult(validationResult.getLeft(), updateUser, validationResult.getRight());
		} else {
			String updateUserName = updateUser.getUserName();

			List<User> existingUserList = userRepo.findByUserName(updateUserName);

			User existingUser = existingUserList.get(0);

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
			
			String updatePassword = updateUser.getNewPassword();
			if (StringUtils.isNotBlank(updatePassword)){
				existingUser.setPassword(updatePassword);
			}
			
			try {
				userRepo.save(existingUser);
			} catch(DataAccessException e){
				return new UpdateUserResult("Your account was not updated successfully due to an internal error.", updateUser, false);
			}
			
			return new UpdateUserResult("Your account is updated successfully.", updateUser, true);
		} 
	}

	@Override
	public UpdateUserResult createUser(User userObj) {
		Pair<String,Boolean> validationResult = validateForCreate(userObj);
		if (! validationResult.getRight()){
			return new UpdateUserResult(validationResult.getLeft(), userObj, validationResult.getRight());
		} else {
			try {
				userObj.setEnabled(true);
				userRepo.save(userObj);
			} catch(DataAccessException e){
				return new UpdateUserResult("Your account was not created successfully due to an internal error.", userObj, false);
			}
			
			return new UpdateUserResult("Your account is updated successfully.", userObj, true);
		}
	}

	private Pair<String, Boolean> validateForCreate(User userObj) {
		if (userObj  == null){
			return new ImmutablePair<String, Boolean>("Server side error. " + 
					"We are unable to create your account at this time.", 
					false);
		}
		String userName = userObj.getUserName();
		if (StringUtils.isBlank(userName) || userName.length() < 7){
			return new ImmutablePair<String, Boolean>("You did not give us a workable user name.  Please try something else", false);
		}
		
		List<User> existingUserList = userRepo.findByUserName(userName);
		if (existingUserList != null && existingUserList.size() > 0){
			return new ImmutablePair<String,Boolean>("Please try another user name.", false);
		}
		
		if (! meetsCriteria(userObj.getPassword())){
			return new ImmutablePair<String, Boolean>("The password you entered is not strong enough. " +
					"Please use a password that is at least 7 characters.", false);
		}
		
		return new ImmutablePair<String, Boolean>("", true);
	}
}

package com.rootser.bluerootser.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rootser.bluerootser.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	List<User> findByUserName(String userName);
}

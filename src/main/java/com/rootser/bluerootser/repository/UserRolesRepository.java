package com.rootser.bluerootser.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rootser.bluerootser.model.User;
import com.rootser.bluerootser.model.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Integer> {
	List<UserRoles> findByUsername(String username);
}

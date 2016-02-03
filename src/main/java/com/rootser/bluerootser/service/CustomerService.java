package com.rootser.bluerootser.service;

import java.util.List;

import com.rootser.bluerootser.model.Customer;

public interface CustomerService {
	List<Customer> findAll(String userId, String password);
}

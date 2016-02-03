package com.rootser.bluerootser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.model.Customer;
import com.rootser.bluerootser.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public List<Customer> findAll(String userId, String password) {
		
		return  (List<Customer>) customerRepository.findAll();
	}

}

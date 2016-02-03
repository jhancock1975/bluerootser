package com.rootser.bluerootser.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rootser.bluerootser.model.Customer;
import com.rootser.bluerootser.model.CustomerPK;

public interface CustomerRepository extends CrudRepository<Customer, CustomerPK> {
	List<Customer> findByLastname(String lastName);
}

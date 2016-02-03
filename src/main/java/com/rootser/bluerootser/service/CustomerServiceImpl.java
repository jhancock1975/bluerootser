package com.rootser.bluerootser.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.model.Customer;
import com.rootser.bluerootser.model.CustomerPK;
import com.rootser.bluerootser.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Override
	@Cacheable("customers")
	public List<Customer> findAll(String userId, String password) {
		Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
		CustomerPK pk = new CustomerPK();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.debug("caught interrupted exception while waiting ", e);
		}
		pk.setUserid("john");
		pk.setPassword("test1");
		return  (List<Customer>) customerRepository.findAll(Arrays.asList(pk));
	}

}

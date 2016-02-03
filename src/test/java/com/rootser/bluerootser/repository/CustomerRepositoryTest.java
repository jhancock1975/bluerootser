package com.rootser.bluerootser.repository;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rootser.bluerootser.model.Customer;
import com.rootser.bluerootser.model.CustomerPK;
import com.rootser.bluerootser.testconfig.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =TestConfig.class)
@WebAppConfiguration
public class CustomerRepositoryTest {
	@Autowired CustomerRepository customerRepository;
	@Test
	public void testFindAll() {
		CustomerPK pk = new CustomerPK();
		pk.setUserid("john");
		//no hash of a password should equal "test"
		pk.setPassword("test");
		assertTrue("list should be empty", ((List<Customer>) customerRepository.findAll(Arrays.asList(pk))).size()==0);
		
	}
}


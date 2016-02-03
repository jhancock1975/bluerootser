package com.rootser.bluerootser.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rootser.bluerootser.testconfig.TestConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=TestConfig.class)
@WebAppConfiguration
public class CustomerServiceTest {
	Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
	@Autowired 
	private CustomerService customerService;
	private Date date = new Date();
	private String getTime(){
		date.setTime(System.currentTimeMillis());
		return date.toString();
	}
	@Test
	public void testFindAll() {
		logger.debug("time before first call to findAll()" + getTime());
		customerService.findAll("john", "test1");
		logger.debug("time after first call " + getTime());
		customerService.findAll("john", "test1");
		logger.debug("time after second call " + getTime());
	}

}

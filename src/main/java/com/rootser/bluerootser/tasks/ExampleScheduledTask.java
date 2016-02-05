package com.rootser.bluerootser.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rootser.bluerootser.service.CustomerService;

@Component
public class ExampleScheduledTask {
	private static final Logger logger = LoggerFactory.getLogger(ExampleScheduledTask.class);
	@Autowired
	CustomerService custService;
	
	@Scheduled(cron="0 0/1 * * * *")
	public void printCustomers(){
		logger.debug(custService.findAll("john", "test1").toString());
	}
	
}

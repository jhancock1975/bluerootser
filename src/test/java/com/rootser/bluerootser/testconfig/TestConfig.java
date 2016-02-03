package com.rootser.bluerootser.testconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rootser.bluerootser.service.CustomerService;
import com.rootser.bluerootser.service.CustomerServiceImpl;

@Configuration
@ComponentScan("com.rootser")
public class TestConfig {
	@Bean
	CustomerService customerService(){
		return new CustomerServiceImpl();
	}

}

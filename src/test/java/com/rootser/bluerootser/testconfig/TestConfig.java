package com.rootser.bluerootser.testconfig;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.rootser.bluerootser"})
@EnableCaching
public class TestConfig {
	

}

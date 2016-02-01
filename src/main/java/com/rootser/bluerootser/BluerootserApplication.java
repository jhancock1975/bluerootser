package com.rootser.bluerootser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication

public class BluerootserApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BluerootserApplication.class, args);
	}
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(BluerootserApplication.class);
	     }
}

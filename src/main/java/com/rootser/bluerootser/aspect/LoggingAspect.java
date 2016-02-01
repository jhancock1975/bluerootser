package com.rootser.bluerootser.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	@Before("execution(* com.rootser.bluerootser.controller.HelloWorldController.hello(..))")
	public void logBefore(JoinPoint joinPoint){
		logger.debug("before hello execution");
	}
}

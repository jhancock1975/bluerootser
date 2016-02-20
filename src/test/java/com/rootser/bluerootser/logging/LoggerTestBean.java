package com.rootser.bluerootser.logging;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerTestBean {
	private static @InjectLogger Logger LOGGER;
	
	public void logSomething() {
		LOGGER.debug("logging test");
	}

}

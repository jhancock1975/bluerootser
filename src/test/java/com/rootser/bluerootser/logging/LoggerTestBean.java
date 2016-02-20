package com.rootser.bluerootser.logging;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerTestBean {
	public static @InjectLogger Logger LOGGER;

}

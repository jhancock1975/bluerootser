package com.rootser.bluerootser.logging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:injectLoggerTest.xml" })
public class InjectLoggerTest {
	@Autowired
	private LoggerTestBean testBean;
	@Test
	public void test() {
		testBean.logSomething();
	}

}

package com.rootser.bluerootser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rootser.bluerootser.BluerootserApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BluerootserApplication.class)
@WebAppConfiguration
public class BluerootserApplicationTests {

	@Test
	public void contextLoads() {
	}

}

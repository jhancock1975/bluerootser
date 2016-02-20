package com.rootser.bluerootser.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rootser.bluerootser.testconfig.TestConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=TestConfig.class)
@WebAppConfiguration
public class ArticleFetchSvcImplTest {

	@Autowired
	private ArticleFetchSvcImpl articleSvc;
	@Test
	public void testGetArticles() {
		articleSvc.getArticles(1);
	}

}

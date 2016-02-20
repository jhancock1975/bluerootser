package com.rootser.bluerootser.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rootser.bluerootser.model.Article;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:articleFetchServiceTest.xml" })
public class ArticleFetchSvcImplTest {

	@Autowired
	private ArticleFetchSvcImpl articleSvc;
	@Test
	public void testGetArticles() {
		List<Article> articles = articleSvc.getArticles(1 ,"http://www.cna.com.tw/");
		assertTrue(articles.size() == 1);
	}

}

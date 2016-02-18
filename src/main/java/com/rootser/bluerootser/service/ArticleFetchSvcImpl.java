package com.rootser.bluerootser.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rootser.bluerootser.model.Article;

public class ArticleFetchSvcImpl implements ArticleFetchSvc {
	private static final Logger logger = LoggerFactory.getLogger(ArticleFetchSvcImpl.class);
	@Override
	public List<Article> getArticles(int numArticles) {
		try {
			Document doc = Jsoup.connect("http://http://www.cna.com.tw/").get();
		} catch (IOException e) {
			logger.debug("i/o exception connecting to url ", e);
		}
		return null;
	}

}

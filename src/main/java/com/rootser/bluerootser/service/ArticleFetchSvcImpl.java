package com.rootser.bluerootser.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.model.Article;
@Service
public class ArticleFetchSvcImpl implements ArticleFetchSvc {
	private static final Logger logger = LoggerFactory.getLogger(ArticleFetchSvcImpl.class);
	@Override
	public List<Article> getArticles(int numArticles) {
		try {
			Document doc = Jsoup.connect("http://www.cna.com.tw/").get();
			Elements links = doc.select("a[href*=news]");
			for (Element link: links){
				logger.debug(link.html());
			}
		} catch (IOException e) {
			logger.debug("i/o exception connecting to url ", e);
		}
		return null;
	}

}

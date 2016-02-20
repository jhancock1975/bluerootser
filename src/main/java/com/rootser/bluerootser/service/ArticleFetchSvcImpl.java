package com.rootser.bluerootser.service;

import java.io.IOException;
import java.util.ArrayList;
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
	public List<Article> getArticles(int numArticles, String baseURl) {
		List<Article> result = new ArrayList<Article>();
		try {
			String ua = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36";
			Document doc = Jsoup.connect(baseURl).userAgent(ua).get();
			Elements links = doc.select("a[href*=/news/]");
			int count = 1;
			for (Element link: links){
				logger.debug(link.attr("href"));
				String articleUrl = link.attr("href");
				if (! articleUrl.contains("http")){
					articleUrl = baseURl + articleUrl;
				}
				Document articleDoc = Jsoup.connect(articleUrl).userAgent(ua).get();
				result.add(new Article(articleUrl, articleDoc.html()));
				if (++count > numArticles){
					break;
				}
			}
		} catch (IOException e) {
			logger.debug("i/o exception connecting to url ", e);
		}
		return result;
	}

}

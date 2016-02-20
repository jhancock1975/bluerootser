package com.rootser.bluerootser.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.logging.InjectLogger;
import com.rootser.bluerootser.model.Article;
@Service
public class ArticleFetchSvcImpl implements ArticleFetchSvc {
	private static @InjectLogger Logger logger;
	
	public static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36";
	
	@Override
	public List<Article> getArticles(int numArticles, String baseUrl, String urlPattern ){
		List<Article> result = new ArrayList<Article>();
		try {
			List<String> urls = getArticleUrls(numArticles, baseUrl, urlPattern);
			int count = 1;
			for (String url: urls){
				logger.debug(url);
				Document articleDoc = Jsoup.connect(url).userAgent(USER_AGENT).get();
				result.add(new Article(url, articleDoc.html()));
				if (++count > numArticles){
					break;
				}
			}
		} catch (IOException e) {
			logger.debug("i/o exception connecting to url ", e);
		}
		return result;
	}

	@Override
	public List<String> getArticleUrls(int numArticles, String baseUrl, String urlPattern) {
		List<String> result = new ArrayList<String>();
		try {
			String ua = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36";
			Document doc = Jsoup.connect(baseUrl).userAgent(ua).get();
			Elements links = doc.select("a[href*=" + urlPattern + "]");
			int count = 1;
			for (Element link: links){
				logger.debug(link.attr("href"));
				String articleUrl = link.attr("href");
				if (! articleUrl.contains("http")){
					articleUrl = baseUrl + articleUrl;
				}
				result.add(articleUrl);
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

package com.rootser.bluerootser.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rootser.bluerootser.logging.InjectLogger;
import com.rootser.bluerootser.model.Article;
@Service
public class ArticleFetchSvcImpl implements ArticleFetchSvc {
	private static @InjectLogger Logger logger;
	
	public static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36";
	
	@Override
	@Cacheable("articles")
	public List<Article> getArticles(int numArticles, String baseUrl, String urlPattern, String tagSelector ){
		List<Article> result = new ArrayList<Article>();
		try {
			List<String> urls = getArticleUrls(numArticles, baseUrl, urlPattern);
			int count = 1;
			for (String url: urls){
				logger.debug(url);
				Document articleDoc = Jsoup.connect(url).userAgent(USER_AGENT).get();
				articleDoc = fixRelativeUrls(articleDoc, tagSelector, baseUrl);
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

	/**
	 * added this after we saw html of articles containing image elements with
	 * relative paths in src attribute
	 * 
	 * images with relative paths then get sent back to browser
	 * 
	 * browser sends requests for images to us, which we cannot serve
	 * 
	 * this hack sticks the base url back onto the src attribute 
	 * 
	 * @param articleDoc
	 * @param tagSelector
	 * @param baseUrl
	 * @return
	 */
	private Document fixRelativeUrls(Document articleDoc, String tagSelector, String baseUrl) {
		Document articleHtmlDoc = Jsoup.parse(articleDoc.select(tagSelector).html());
		for (Element imgElt : articleHtmlDoc.select("img")){
			if (imgElt.absUrl("src").trim().startsWith("/")){
				imgElt.setBaseUri(baseUrl);
			}
		}
		return articleHtmlDoc;
	}

	@Override
	@Cacheable("urls")
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

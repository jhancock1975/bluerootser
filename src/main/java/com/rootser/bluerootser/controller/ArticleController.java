package com.rootser.bluerootser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rootser.bluerootser.constants.Constants;
import com.rootser.bluerootser.model.Article;
import com.rootser.bluerootser.service.ArticleFetchSvc;
@Controller
public class ArticleController {
	@Autowired
	private ArticleFetchSvc fetchSvc;

	@RequestMapping(value = Constants.articles, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody String getArticles() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");

		List<Article> articles= fetchSvc.getArticles(10, "http://www.cna.com.tw", "/news/", "section[itemprop=articleBody");

		StringBuilder result = new StringBuilder();
		for (Article article: articles){
			result.append(article.getText());
		}
		return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head><body>" 
			+ result.toString() + "</body></html>";
	}
}
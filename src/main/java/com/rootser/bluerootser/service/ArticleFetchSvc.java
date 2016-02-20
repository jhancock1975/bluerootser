package com.rootser.bluerootser.service;

import java.util.List;

import com.rootser.bluerootser.model.Article;

public interface ArticleFetchSvc {
	List<Article> getArticles(int numArticles, String baseUrl, String urlPattern);

}

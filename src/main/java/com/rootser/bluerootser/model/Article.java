package com.rootser.bluerootser.model;

import org.springframework.stereotype.Component;

@Component
public class Article {
	public String url;
	public String text;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}

package com.rootser.bluerootser.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="articles")
public class Article {
	@Id private Long id;
	private String url;
	private String text;
	public Article(String articleUrl, String html) {
		this.url = articleUrl;
		this.text=html;
	}
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

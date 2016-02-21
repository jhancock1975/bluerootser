package com.rootser.bluerootser.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rootser.bluerootser.logging.InjectLogger;
import com.rootser.bluerootser.service.ArticleFetchSvc;
@Controller
public class IndexController {
	private static @InjectLogger  Logger logger;
	 
	@Autowired
	 private ArticleFetchSvc fetchSvc;
	 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getArticles() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        /*List<Article> articles= fetchSvc.getArticles(10, "http://www.cna.com.tw", "/news/");
        
        mav.addObject("articles", articles);*/
 
        return mav;
    }
 
}

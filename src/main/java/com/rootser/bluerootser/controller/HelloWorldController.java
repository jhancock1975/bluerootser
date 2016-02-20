package com.rootser.bluerootser.controller;
 
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rootser.bluerootser.logging.InjectLogger;
 
@Controller
public class HelloWorldController {
	
	private static @InjectLogger  Logger logger;
 
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
         
        String str = "Hello World!";
        mav.addObject("message", str);
 
        return mav;
    }
 
}

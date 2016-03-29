package com.rootser.bluerootser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rootser.bluerootser.constants.Constants;

@Controller
public class MemorizationTechniquesController {
	@RequestMapping(value = "/" + Constants.memorizationTechniques, method = RequestMethod.GET)
	public ModelAndView getHelp(){
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("memorizationTechniques");
	    return mav;
	}
}

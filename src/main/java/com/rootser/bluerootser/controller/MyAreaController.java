package com.rootser.bluerootser.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rootser.bluerootser.constants.Constants;
import com.rootser.bluerootser.logging.InjectLogger;
import com.rootser.bluerootser.model.User;
import com.rootser.bluerootser.repository.UserRepository;

@Controller
public class MyAreaController {
	@InjectLogger  Logger logger;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value = "/" + Constants.myArea, method = RequestMethod.GET)
	public ModelAndView getMyArea(){
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    List<User> curUserList = userRepo.findByUserName(name);
	    mav.addObject("curUserList", curUserList);
	    mav.setViewName(Constants.myArea);
	    return mav;
	}
}

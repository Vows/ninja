package com.vows.ninja.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vows.ninja.data.access.Profile;
import com.vows.ninja.data.access.ProfileDAOJDBCImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		logger.debug("Test Log");
		return "home";
	}
	
	/**
	 * Add an entity
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody 
	public Profile addComputer(@RequestBody Profile profile) {
	  ProfileDAOJDBCImpl impl = ProfileDAOJDBCImpl.getInstance();
	  impl.create(profile);
	  return impl.get(profile.getProfile_id());
	}
	
	/**
	 * Get an entity
	 */
	@RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
	public @ResponseBody Profile getProfile(@PathVariable int id) {
		  ProfileDAOJDBCImpl impl = ProfileDAOJDBCImpl.getInstance();
		  System.out.println(id);
		  return impl.get(id); 
	}
	
	/**
	 * Get an entity
	 */
	@RequestMapping(value="/profiles/{id}", method = RequestMethod.GET)
	public @ResponseBody Profile getProfiles(@PathVariable int id) {
		  System.out.println(id);
		  Profile p = new Profile();
		  p.setProfile_id(id);
		  p.setProfile_name("Akshay");
		  return p;
		   
	}
}

package com.quramsoft.atomjpeg.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.quramsoft.atomjpeg.model.SearchInput;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static final String USER_HOME = System.getProperty("user.home");
	private static final String USER_DIR = System.getProperty("user.dir");
	private static final String USER_NAME = System.getProperty("user.name");
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String PATH_SEPARATOR = System.getProperty("path.separator");

	private static final String SERVER_IP = "http://192.168.0.178";
	private static final String SERVER_PORT = "8090";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("IP address of host: "+IP.getHostAddress());
		System.out.println("Configured IP address: " + SERVER_IP);
		System.out.println("Configured port: " + SERVER_PORT);
		System.out.println("User home path: " + USER_HOME);

		
		return "home";
	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showForm(Model model) {

		
		model.addAttribute("searchinput", new SearchInput());
		
		
		
		
		
		
		
		return "main"; // dynamic form using Spring's form tag library

	}
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public @ResponseBody ModelAndView resultForm(SearchInput searchInput) {
		ModelAndView mv = new ModelAndView("result");

		
		return mv; // return model and view

	}
	
	
	
	
	
	
	
}
	
	

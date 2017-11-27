package com.quramsoft.atomjpeg.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
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

import com.quramsoft.atomjpeg.main.AtomCrawler;
import com.quramsoft.atomjpeg.model.SearchInput;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private static final String USER_HOME = System.getProperty("user.home") + File.separator;
	private static final String USER_DIR = System.getProperty("user.dir");
	private static final String USER_NAME = System.getProperty("user.name");
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String PATH_SEPARATOR = System.getProperty("path.separator");

	private static final String SERVER_IP = "http://192.168.0.178";
	private static final String SERVER_PORT = "8090";

	final static String ORIGINAL_IMAGE_PATH = USER_HOME + "AtomJPEG" + File.separator + "resources" + File.separator
			+ "images" + File.separator + "org";
	final static String ATOM_IMAGE_PATH = USER_HOME + "AtomJPEG" + File.separator + "resources" + File.separator
			+ "images" + File.separator + "atom";

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("IP address of host: " + IP.getHostAddress());
		System.out.println("Configured IP address: " + SERVER_IP);
		System.out.println("Configured port: " + SERVER_PORT);
		System.out.println("User home path: " + USER_HOME);

		return "home";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showForm(Model model) {

		model.addAttribute("searchinput", new SearchInput());

		// if new request delete the files of original and compressed images

		File org = new File(ORIGINAL_IMAGE_PATH);
		File atom = new File(ATOM_IMAGE_PATH);
		// File org_cached = new File(cached_original_image_path);
		// File atom_cached = new File(cached_compressed_image_path);

		File[] org_listOfFiles = org.listFiles();
		File[] atom_listOfFiles = atom.listFiles();
		// File[] org_cached_listOfFiles = org_cached.listFiles();
		// File[] atom_cached_listOfFiles = atom_cached.listFiles();

		if (org.isDirectory() && atom.isDirectory()) {
			for (int i = 0; i < org_listOfFiles.length; i++) {
				if (org_listOfFiles[i].isFile()) {
					System.out.println("File " + org_listOfFiles[i].getName() + "is getting deleted");
					org_listOfFiles[i].delete();
				}
			}
			for (int i = 0; i < atom_listOfFiles.length; i++) {
				if (atom_listOfFiles[i].isFile()) {
					System.out.println("File " + atom_listOfFiles[i].getName() + "is getting deleted");
					atom_listOfFiles[i].delete();
				}
			}
		}

		// if (org_cached.isDirectory() && atom_cached.isDirectory()) {
		// for (int i = 0; i < org_cached_listOfFiles.length; i++) {
		// if (org_cached_listOfFiles[i].isFile()) {
		// System.out.println("File " + org_cached_listOfFiles[i].getName() + "is
		// getting deleted");
		// org_cached_listOfFiles[i].delete();
		// }
		// }
		// for (int i = 0; i < atom_cached_listOfFiles.length; i++) {
		// if (atom_cached_listOfFiles[i].isFile()) {
		// System.out.println("File " + atom_cached_listOfFiles[i].getName() + "is
		// getting deleted");
		// atom_cached_listOfFiles[i].delete();
		// }
		// }

		return "main"; // dynamic form using Spring's form tag library

	}

	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public @ResponseBody ModelAndView resultForm(SearchInput searchInput) {
		ModelAndView mv = new ModelAndView("result");

		int less_bandwidth = 0;
		int average_size_reduction = 0;
		
		ArrayList<String> listOfImageUrls = new ArrayList<String>();
		//listOfImageUrls = AtomCrawler.returnAllImageURLs(searchInput.getUrl(), searchInput.isJpg(), searchInput.isPng(), searchInput.isGif());
		
		
		
		
		
		
		
		
		
		
		
		return mv; // return model and view

	}

}

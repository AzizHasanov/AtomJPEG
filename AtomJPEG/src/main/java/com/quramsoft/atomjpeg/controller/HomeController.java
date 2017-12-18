package com.quramsoft.atomjpeg.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;

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

	private static final String USER_HOME = System.getProperty("user.home") + File.separator;

	private final static String ORIGINAL_IMAGE_PATH = USER_HOME + "AtomJPEG" + File.separator + "resources"
			+ File.separator + "images" + File.separator + "org";
	private final static String ATOM_IMAGE_PATH = USER_HOME + "AtomJPEG" + File.separator + "resources" + File.separator
			+ "images" + File.separator + "atom";

	private ArrayList<ArrayList<String>> allImageResults;
	private int numberOfImages;
	private int less_bandwidth;
	private int size_reduction_percentage;
	private double timeToCompress; // in seconds

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("searchinput", new SearchInput());

		// if new request delete the files of original and compressed images
		cleanTemporalFolders();

		return "ready";
	}

	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public @ResponseBody ModelAndView resultReport(SearchInput searchInput) {
		ModelAndView mv = new ModelAndView("report");

		cleanTemporalFolders();

		less_bandwidth = 0;
		int average_size_reduction = 0;

		ArrayList<String> listOfImageUrls = new ArrayList<String>();

		Instant instBeforeCrawling = Instant.now();
		listOfImageUrls = AtomCrawler.returnAllCrawledImageURLs(searchInput.getUrl(), searchInput.isJpg(),
				searchInput.isPng(), searchInput.isGif());
		Instant instAfterCrawling = Instant.now();

		System.out.println("STEP 1: All image URLs crawled! ");

		ArrayList<String> downloadedImageFinalNames = new ArrayList<String>();
		Instant instBeforeDownload = Instant.now();
		downloadedImageFinalNames = AtomCrawler.downloadImages(listOfImageUrls);
		Instant instAfterDownload = Instant.now();

		System.out.println("STEP 2: All images downloaded! ");

		ArrayList<String> recompressedImageFinalNames = new ArrayList<String>();

		Instant instBeforeCompression = Instant.now();
		recompressedImageFinalNames = AtomCrawler.recompressImages(downloadedImageFinalNames, searchInput.getProfile(),
				searchInput.getLevel());
		Instant instAfterCompression = Instant.now();

		Duration durationCrawling = Duration.between(instBeforeCrawling, instAfterCrawling);
		Duration durationDownload = Duration.between(instBeforeDownload, instAfterDownload);
		Duration durationCompression = Duration.between(instBeforeCompression, instAfterCompression);

		double secDurationCrawling = durationCrawling.toMillis() / 1000.0; // in seconds
		double secDurationDownload = durationDownload.toMillis() / 1000.0; // in seconds
		double secDurationCompression = durationCompression.toMillis() / 1000.0; // in seconds

		timeToCompress = secDurationCompression;

		System.out.println("Time taken to crawl all images: " + durationCrawling.toMillis() + " milliseconds" + " or "
				+ secDurationCrawling + " seconds");
		System.out.println("Time taken to download all images: " + durationDownload.toMillis() + " milliseconds"
				+ " or " + secDurationDownload + " seconds");
		System.out.println("Time taken to compress all images: " + durationCompression.toMillis() + " milliseconds"
				+ " or " + secDurationCompression + " seconds");

		System.out.println("STEP 3: All images recompressed! ");

		// MetaArray
		allImageResults = new ArrayList<ArrayList<String>>();
		for (int j = 0; j < recompressedImageFinalNames.size(); j++) {

			File file_compressed = new File(recompressedImageFinalNames.get(j));
			File file_original = new File(downloadedImageFinalNames.get(j));
			BufferedImage bimg = null;
			int width = 0;
			int height = 0;
			try {
				bimg = ImageIO.read(new File(downloadedImageFinalNames.get(j)));
				width = bimg.getWidth();
				height = bimg.getHeight();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			double compressed_file_size = 0;
			double original_file_size = 0;

			if (file_compressed.exists()) {
				compressed_file_size = file_compressed.length();// Double.toString(file_compressed_bytes);
			} else {
				System.out.println("the file" + recompressedImageFinalNames.get(j) + " doesnot exist");
			}
			if (file_original.exists()) {
				original_file_size = file_original.length();// Double.toString(file_original.length()/1);
			} else {
				System.out.println("the file" + downloadedImageFinalNames.get(j) + " doesnot exist");
			}

			// --- Get resolution
			Double compression_ratio = null;
			try {
				compression_ratio = 100 - ((compressed_file_size / original_file_size) * 100);// in percentile
			} catch (ArithmeticException e) {
				System.err.println("Caught IOException: " + e.getMessage());
			}

			String recompressImageLocForJsp;

			String[] temp_names_recompress = recompressedImageFinalNames.get(j).split("/");
			recompressImageLocForJsp = "/" + temp_names_recompress[temp_names_recompress.length - 3];
			recompressImageLocForJsp += "/" + temp_names_recompress[temp_names_recompress.length - 2];
			recompressImageLocForJsp += "/" + temp_names_recompress[temp_names_recompress.length - 1];

			String originalImageLocForJsp;

			String[] temp_names_original = downloadedImageFinalNames.get(j).split("/");
			originalImageLocForJsp = "/" + temp_names_original[temp_names_original.length - 3];
			originalImageLocForJsp += "/" + temp_names_original[temp_names_original.length - 2];
			originalImageLocForJsp += "/" + temp_names_original[temp_names_original.length - 1];

			ArrayList<String> ImageObject = new ArrayList<String>();
			ImageObject.add(originalImageLocForJsp);
			ImageObject.add(recompressImageLocForJsp);
			ImageObject.add(String.valueOf(original_file_size));
			ImageObject.add(String.valueOf(compressed_file_size));

			less_bandwidth += (int) (original_file_size - compressed_file_size);
			average_size_reduction += compression_ratio;

			ImageObject.add(String.valueOf(String.format("%.1f", compression_ratio)));
			ImageObject.add(String.valueOf(width) + "x" + String.valueOf(height));
			ImageObject.add(listOfImageUrls.get(j));
			allImageResults.add(ImageObject);
			System.out.println("############");
			System.out.println("ImageObject " + j + ":" + ImageObject);
		}
		try {
			size_reduction_percentage = average_size_reduction / downloadedImageFinalNames.size();
		} catch (ArithmeticException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
		numberOfImages = downloadedImageFinalNames.size();
		mv.addObject("numberOfImages", downloadedImageFinalNames.size());
		mv.addObject("less_bandwidth", less_bandwidth);
		mv.addObject("size_reduction_percentage", size_reduction_percentage);
		mv.addObject("timeToCompress", timeToCompress);
		// mv.addObject("annual_cdn_savings", annual_cdn_savings); // Need to know the
		// formula to calculate annual CDN savings

		return mv;

	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public @ResponseBody ModelAndView resultImages(SearchInput searchInput) {
		ModelAndView mv = new ModelAndView("result");

		mv.addObject("numberOfImages", numberOfImages);
		mv.addObject("less_bandwidth", less_bandwidth);
		mv.addObject("size_reduction_percentage", size_reduction_percentage);
		mv.addObject("timeToCompress", timeToCompress);

		mv.addObject("allImageResults", allImageResults);

		return mv;
	}

	public void cleanTemporalFolders() {
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
					System.out.println("File " + org_listOfFiles[i].getName() + " is deleted");
					org_listOfFiles[i].delete();
				}
			}
			for (int i = 0; i < atom_listOfFiles.length; i++) {
				if (atom_listOfFiles[i].isFile()) {
					System.out.println("File " + atom_listOfFiles[i].getName() + " is deleted");
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
	}

}

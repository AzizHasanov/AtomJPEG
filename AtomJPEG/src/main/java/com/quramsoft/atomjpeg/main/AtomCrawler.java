/**
 * 
 */
package com.quramsoft.atomjpeg.main;

import org.jsoup.Jsoup;
//import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.atom.utils.AtomUtils;
import com.atom.utils.AtomUtils.AtomOptions;

import java.awt.List;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AtomCrawler {

	private static final String USER_HOME = System.getProperty("user.home") + File.separator;

	final static String ORIGINAL_IMAGE_PATH = USER_HOME + "AtomJPEG" + File.separator + "resources" + File.separator
			+ "images" + File.separator + "org";
	final static String ATOM_IMAGE_PATH = USER_HOME + "AtomJPEG" + File.separator + "resources" + File.separator
			+ "images" + File.separator + "atom";

//	final static String ORIGINAL_IMAGE_PATH = File.separator + "images" + File.separator + "org";
//	final static String ATOM_IMAGE_PATH = File.separator + "images" + File.separator + "atom";
//	
	final static String ORIGINAL_IMAGE_NAME_TEMPLATE = "crawled_";
	final static String ATOM_IMAGE_NAME_TEMPLATE = "crawled_atom_";
	// final static String ATOM_IMAGE_NAME_TEMPLATE = "crawled_*_atom_";

	static ArrayList<String> listOfCrawledImageURLs = new ArrayList<String>();

	
	public static ArrayList<String> returnAllCrawledImageURLs(String url, boolean bJpg, boolean bPng, boolean bGif) {
		// 1
		//ArrayList<String> listOfCrawledImageURLs = new ArrayList<String>();

		ArrayList<String> imageoptions = new ArrayList<String>();
		String selectParameter = "";
		if (bPng == true)
			imageoptions.add("png");
		if (bJpg == true)
			imageoptions.add("jpe?g");
		if (bGif == true)
			imageoptions.add("gif");

		for (int i = 0; i < imageoptions.size(); i++) {

			if (i > 0)
				selectParameter += "|";
			selectParameter += imageoptions.get(i);

		}

		String selectStatement = "img[src~=(?i)\\.(" + selectParameter + ")]";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			// Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			Elements images = doc.select(selectStatement);
			System.out.println("Total number of image links:" + images.size());
			int i = 0;
			for (Element image : images) {
				String imageLink = image.attr("src");
				if (imageLink.length() > 0 & imageLink.length() < 4) {
					imageLink = doc.baseUri() + imageLink.substring(1);
				} else if (!imageLink.substring(0, 4).equals("http"))
					imageLink = doc.baseUri() + "/" + imageLink; // .substring(1)

				System.out.println(imageLink);
				listOfCrawledImageURLs.add(imageLink);
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
		return listOfCrawledImageURLs;

	}


	// Download images using url
	public static ArrayList<String> downloadImages(ArrayList<String> listOfImageUrls) {
		// 2
				ArrayList<String> downloadedImageFinalNames = new ArrayList<String>();
				int numberOfImages = listOfImageUrls.size();
				

				for (int i = 0; i < numberOfImages; i++) {			
					
					String imageExtension = listOfImageUrls.get(i).substring(listOfImageUrls.get(i).length() - 4);			
								
					downloadedImageFinalNames.add(downloadImage(listOfImageUrls.get(i),	ORIGINAL_IMAGE_NAME_TEMPLATE + i + imageExtension, ORIGINAL_IMAGE_PATH));
				}
				return downloadedImageFinalNames;

	}

	// Download image using url
	public static String downloadImage(String imageUrl, String imageName, String destName) {
		// 3
		URL url;
		String orgImageFinalName = "";
		try {
			url = new URL(imageUrl);

			// String fileName = url.getFile();
			File rootTargetDir = new File(destName);
			if (!rootTargetDir.exists()) {
				boolean created = rootTargetDir.mkdirs();
				if (!created) {
					System.out.println("Error while creating directory for location- " + destName);
				}
			}

			orgImageFinalName = destName + File.separator + imageName;

			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(orgImageFinalName);

			byte[] b = new byte[5120];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			System.out.println("Downloaded original image: " + orgImageFinalName);
			System.out.println("***********************");

			is.close();
			os.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return orgImageFinalName;

	}

	// Image Compressor (Re-compress images by AtomJPEG (example))
	public static ArrayList<String> recompressImages(ArrayList<String> listOfImageSrcs, String profile, String level) {
		ArrayList<String> recompressedImageFinalNames = new ArrayList<String>();
		int numberOfImages = listOfImageSrcs.size();
		String atomImageName;
		for (int i = 0; i < numberOfImages; i++) {
			/*
			 * File file = new File(listOfImageSrcs.get(i)); if (file.length() < 1000){
			 * continue;//this filters images below 1000 bytes }
			 */
			// atomImageName = ATOM_IMAGE_NAME_TEMPLATE.replace('*', (char) i);
			String imageExtension = null;
			if (listOfCrawledImageURLs.get(i).length() > 4)
				{
					imageExtension = listOfCrawledImageURLs.get(i).substring(listOfCrawledImageURLs.get(i).length() - 4);			
				}

			
			atomImageName = ATOM_IMAGE_NAME_TEMPLATE + i + imageExtension;
			recompressedImageFinalNames
					.add(recompressImage(listOfImageSrcs.get(i), atomImageName, ATOM_IMAGE_PATH, profile, level));

			
			
		}
		return recompressedImageFinalNames;

	}

	public static String recompressImage(String imageSrc, String imageName, String destName, String profile,
			String level) {

		AtomOptions ao = new AtomOptions();

		switch (profile) {
		case "Fast":
			ao.profile = AtomUtils.PROFILE_FAST;
			break;
		case "Baseline":
			ao.profile = AtomUtils.PROFILE_BASELINE;
			break;
		case "Main":
			ao.profile = AtomUtils.PROFILE_MAIN;
			break;
		case "High":
			ao.profile = AtomUtils.PROFILE_HIGH;
			break;

		}

		switch (level) {
		case "High Quality":
			ao.level = AtomUtils.HIGH_QUALITY;
			break;
		case "Normal":
			ao.level = AtomUtils.NORMAL_QUALITY;
			break;
		case "High Compression":
			ao.level = AtomUtils.HIGH_COMPRESSION;
			break;
		case "Extreme":
			ao.level = AtomUtils.EXTREME_COMPRESSION;
			break;

		}
		ao.keepFileFormat = 1; // it must be 1
		ao.width = 0; // 0 - converted to the same size as the original width
		ao.height = 0; // 0 - converted to the same size as the original height

		File rootTargetDir = new File(destName);
		if (!rootTargetDir.exists()) {
			boolean created = rootTargetDir.mkdirs();
			if (!created) {
				System.out.println("Error while creating directory for location- " + destName);
			}
		}

		String atomImageFinalName = destName + File.separator + imageName;

		int sizeOfAtomJPEG = AtomUtils.convertJpegToAtomJPEG(imageSrc, atomImageFinalName, ao);
		// AtomUtils au = new AtomUtils();
		// int sizeOfAtomJPEG = au.convertJpegToAtomJPEG(srcImg1, destImg1, ao);
		System.out.println("Converted Atom image: " + atomImageFinalName);
		System.out.println("sizeOfAtomJPEG: " + sizeOfAtomJPEG);
		return atomImageFinalName;

	}

	public static void main(String[] args) throws IOException {

		// returnAllImageURLs("http://www.quramsoft.com");
	}

	public static void AtomJPEG() {
		AtomOptions ao = new AtomOptions();
		ao.profile = AtomUtils.PROFILE_MAIN;
		ao.level = AtomUtils.NORMAL_QUALITY;
		ao.keepFileFormat = 1; // it must be 1
		ao.width = 0;
		ao.height = 0;

		// (If you specify the width and height to zero is converted to the same size as
		// the original resolution.)

		String outputPath = null;
		String srcPath = null;
		long resultSize = AtomUtils.convertJpegToAtomJPEG(srcPath, outputPath, ao);

	}

	public void getFormParams(String html) {

		Document doc = Jsoup.parse(html);

		// HTML form id
		Element loginform = doc.getElementById("your_form_id");
		Elements inputElements = loginform.getElementsByTag("input");

		ArrayList<String> paramList = new ArrayList<String>();
		for (Element inputElement : inputElements) {
			String key = inputElement.attr("name");
			String value = inputElement.attr("value");
		}
	}

	// To save the image
	public static void saveByteBufferImage(ByteBuffer imageDataBytes, String rootTargetDirectory,
			String savedFileName) {
		String uploadInputFile = rootTargetDirectory + savedFileName;

		File rootTargetDir = new File(rootTargetDirectory);
		if (!rootTargetDir.exists()) {
			boolean created = rootTargetDir.mkdirs();
			if (!created) {
				System.out.println("Error while creating directory for location- " + rootTargetDirectory);
			}
		}
		String[] fileNameParts = savedFileName.split("\\.");
		String format = fileNameParts[fileNameParts.length - 1];

		File file = new File(uploadInputFile);
		BufferedImage bufferedImage;

		InputStream in = new ByteArrayInputStream(imageDataBytes.array());
		try {
			bufferedImage = ImageIO.read(in);
			ImageIO.write(bufferedImage, format, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Image Pop-up
	/*
	 * public static void MyModal(){ // Get the modal
	 * 
	 * var modal = document.getElementById('myModal');
	 * 
	 * // Get the image and insert it inside the modal - use its "alt" text as a
	 * caption var img = document.getElementById('myImg'); var modalImg =
	 * document.getElementById("img01"); var captionText =
	 * document.getElementById("caption"); img.onclick = function(){
	 * modal.style.display = "block"; modalImg.src = this.src; captionText.innerHTML
	 * = this.alt; }
	 * 
	 * // Get the <span> element that closes the modal var span =
	 * document.getElementsByClassName("close")[0];
	 * 
	 * // When the user clicks on <span> (x), close the modal span.onclick =
	 * function() { modal.style.display = "none"; } }
	 */

	// To download image
	public static String storeImageIntoFS(String imageUrl, String fileName, String relativePath) {
		String imagePath = null;
		try {
			int indexOfCom = imageUrl.lastIndexOf("com/");
			String mainWebsiteUrl = imageUrl.substring(indexOfCom);
			// String url =
			// byte[] bytes =
			// Jsoup.connect("http://www.quramsoft.com/").ignoreContentType(true).execute().bodyAsBytes();

			byte[] bytes = Jsoup.connect("http://www.quramsoft.com/").ignoreContentType(true).execute().bodyAsBytes();

			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			// String rootTargetDirectory = fileName + "/" + relativePath; // IMAGE_HOME
			// imagePath = rootTargetDirectory + "/" + fileName;

			saveByteBufferImage(buffer, relativePath, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagePath;
	}

	// Image Pop-up
	/*
	 * public static void MyModal(){ // Get the modal
	 * 
	 * var modal = document.getElementById('myModal');
	 * 
	 * // Get the image and insert it inside the modal - use its "alt" text as a
	 * caption var img = document.getElementById('myImg'); var modalImg =
	 * document.getElementById("img01"); var captionText =
	 * document.getElementById("caption"); img.onclick = function(){
	 * modal.style.display = "block"; modalImg.src = this.src; captionText.innerHTML
	 * = this.alt; }
	 * 
	 * // Get the <span> element that closes the modal var span =
	 * document.getElementsByClassName("close")[0];
	 * 
	 * // When the user clicks on <span> (x), close the modal span.onclick =
	 * function() { modal.style.display = "none"; } }
	 */

}

/*
 * /////////////////////////////////////////////////////////////////////////////
 * ////////////////// /* When you receive an image crawl request, empty the
 * temporary folder. Please create a temporary directory of crawled images and
 * save them in there. example) if temporary directory name is temp
 * temp/org/crawled_001.jpg temp/org/crawled_002.jpg temp/org/crawled_003.jpg
 * and so on... Then recompressed images are save into atom directory ex)
 * temp/atom/crawled_001_atom.jpg temp/atom/crawled_002_atom.jpg and so on... If
 * you press the download button, please compress all temp/org, temp/atom images
 * into one zip file and download the zip file. (please refer to page 3 of PPT)
 */

package com.atom.utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class AtomUtils {
	public static final int HIGH_QUALITY = 0xFF00;
	public static final int NORMAL_QUALITY = 0xFF01;
	public static final int HIGH_COMPRESSION = 0xFF02;
	public static final int EXTREME_COMPRESSION = 0xFF03;

	public static final int HUFFMAN_STANDARD = 0x00;
	public static final int HUFFMAN_OPTIMIZED = 0x01;

	public static final int PROFILE_FAST = 0x0000;
	public static final int PROFILE_BASELINE = 0x0010;
	public static final int PROFILE_MAIN = 0x0100;
	public static final int PROFILE_HIGH = 0x1000;

	public static final int ATOM_RESIZE_FILTER_NONE = 0;
	public static final int ATOM_RESIZE_FILTER_DEFAULT = 1;
	public static final int ATOM_RESIZE_FILTER_LANCZOS3 = 2;
	public static final int ATOM_RESIZE_FILTER_LANCZOS4 = 3;
	public static final int ATOM_RESIZE_FILTER_BICUBIC = 4;
	public static final int ATOM_RESIZE_FILTER_BILINEAR = 5;

	public static final int ATOM_TABLE_TYPE = 2;

	public static final int ATOM_ROTATION_0 = 0;
	public static final int	ATOM_ROTATION_ORIENTATION = 4; 

	public static final int ATOM_IMAGE_UNKNOWN = 0;
	public static final int ATOM_IMAGE_JPEG = 1;
	public static final int ATOM_IMAGE_BMP = 2;
	public static final int ATOM_IMAGE_PNG = 3;
	public static final int ATOM_IMAGE_GIF = 4;
	public static final int ATOM_IMAGE_WBMP = 5;
	public static final int ATOM_IMAGE_AGIF = 6;

	//public static final int ATOM_ERR_NONE = 0;
	public static final int ATOM_ERR_CONVERSION_FAIL			= -0;
	public static final int ATOM_ERR_CONVERSION_FAIL_ON_JNI		= -1;
	public static final int ATOM_ERR_INVALID_INPUT_PARAMETER	= -2;
	public static final int ATOM_ERR_INVALID_INPUT_STREAM_SIZE	= -3;
	public static final int ATOM_ERR_INVALID_INPUT_RESOLUTION	= -4;
	public static final int ATOM_ERR_INVALID_SCALED_RESOLUTION	= -5;
	public static final int ATOM_ERR_FAIL_TO_FSTAT				= -6;
	public static final int ATOM_ERR_FAIL_TO_MMAP				= -7;
	public static final int ATOM_ERR_FAIL_TO_OPEN_INPUT_FILE	= -8;
	public static final int ATOM_ERR_FAIL_TO_OPEN_OUTPUT_FILE	= -9;
	public static final int ATOM_ERR_FAIL_TO_ALLOCATE_MEMORY	= -10;
	public static final int ATOM_ERR_FAIL_TO_CREATE_DECINFO		= -11;
	public static final int ATOM_ERR_FAIL_TO_DECODE				= -12;
	public static final int ATOM_ERR_FAIL_TO_ENCODE				= -13;
	public static final int ATOM_ERR_UNKNOWN_FORMAT				= -14;
	public static final int ATOM_ERR_ABNORMAL_IMAGE_RESOLUTION	= -15;
	public static final int ATOM_ERR_UNSUPPORTED_JPEG_TYPE		= -16;
	public static final int ATOM_ERR_ATOMJPEG_INPUT				= -17;
	public static final int ATOM_ERR_TOO_LOW_QUALITY_INPUT		= -18;
	public static final int ATOM_ERR_TOO_SMALL_OUTPUT_BUFFER	= -19;
	public static final int ATOM_ERR_LICENSE_EXPIRED			= -20;

	static String[] errorMessages = {
		"Conversion Fail",
		"Conversion Fail on JNI", 
		"Invalid Input Parameters",
		"Invalid Input Stream Size", 
		"Invalid Input Width or Height",
		"Scaled Width or Height may be Zero",
		"Fail to Fstat",
		"Fail to Mmap",
		"Fail to Open Input File",
		"Fail to Open Output File",
		"Fail to Allocate Memroy",
		"Fail to Cretae Decinfo",
		"Fail to Decode Image",
		"Fail to Encode Image",
		"Unknown Format",
		"Abnormal Image. (width or height <= 0)",
		"Unsupported Jpeg Type. (Color or Sub-Sampling)",
		"Input is AtomJPEG",
		"Input Image Quality is Too Low. (just copy)",
		"Output Buffer is Too Small",
		"License is Expired",
	};

	static boolean loadLib = false;
	static boolean debugMode = true;
	
	public static AtomicLong memUsage = new AtomicLong();
	public static AtomicLong nInstances = new AtomicLong();
	public static long memoryMaxLimit = 1024 * 1024 * 16 * 10;
	public static long maxInstances = 8;

	static String ATOM_UTILS_TAG = "AtomUtils";

	public static class Log {

		public static void e(String tag, String msg) {
			System.err.println(tag + "\t" + msg);
		}

		public static void d(String tag, String msg) {
			if(debugMode) 
				System.out.println(tag + "\t" + msg);
		}

		public static void i(String tag, String msg) {
			if(debugMode) 
				System.out.println(tag + "\t" + msg);
		}

		public static void v(String tag, String msg) {
			if(debugMode) 
				System.out.println(tag + "\t" + msg);
		}
	}

	public static class AtomOptions {
		public int width = 0;
		public int height = 0;
		public int profile = AtomUtils.PROFILE_BASELINE;
		public int level = AtomUtils.NORMAL_QUALITY;
		public int ratio = 0;
		public int keepExif = 0;
		public int domain = 1;
		public int rotation = AtomUtils.ATOM_ROTATION_0;
		public int tableType = AtomUtils.ATOM_TABLE_TYPE;
		public int atomQuality = 0;
		public int postConversion = 0;
		public int maxTask = 0;
		public int maxSize = 0;
		public int strongMaxSize = 0;
		public int resizeFilter = AtomUtils.ATOM_RESIZE_FILTER_DEFAULT;
		public int ignoreAspectRatio = 0;
		public int supportUpscaling = 0;
		public int fillMargin = 0;
		public int qualityFactor = 0;
		public int quantMethod = 0;
		public int dpi = 0;
		public double scaleFactor = 0;
		public int keepFileFormat = 0;

		public AtomOptions() {
			width = 0;
			height = 0;
			profile = AtomUtils.PROFILE_BASELINE;
			level = AtomUtils.NORMAL_QUALITY;
			ratio = 0;
			keepExif = 0;
			domain = 1;
			rotation = AtomUtils.ATOM_ROTATION_0;
			tableType = AtomUtils.ATOM_TABLE_TYPE;
			atomQuality = 0;
			postConversion = 0;
			maxTask = 0;
			maxSize = 0;
			strongMaxSize = 0;
			resizeFilter = AtomUtils.ATOM_RESIZE_FILTER_DEFAULT;
			ignoreAspectRatio = 0;
			supportUpscaling = 0;
			fillMargin = 0;
			qualityFactor = 0;
			quantMethod = 0;
			dpi = 0;
			scaleFactor = 0;
			keepFileFormat = 0;
		}
	};

	public static class ConversionInfo {
		public long resultSize = 0;
		public long inputSize = 0;
		public String errorMessage = null;

		public ConversionInfo() {
			// TODO Auto-generated constructor stub
			resultSize = 0;
			inputSize = 0;
			errorMessage = null;
		}
	}

	static {
		try {
			System.loadLibrary("atomjpeg");
			setJniEnvironment();
			Log.i(ATOM_UTILS_TAG, "Load complete");
			loadLib = true;
		} catch(Exception e) {
			loadLib = false;
			Log.e(ATOM_UTILS_TAG, "Load fail : " + e.toString());
		}
	}

	public AtomUtils() {

	}



	public static double getQualityOfJPEG(String path) {
		return QrGetQualityOfJPEG(path);
	}

	public static int getJPEGFileSize(String srcName) {
		if( srcName == null )
			return 0;

		return QrGetJPEGInfo( srcName );
	}

	public static String getMimeType(String url, int res[]) {
		int imageType = QrGetImageInfo(url, res);

		if(imageType == ATOM_IMAGE_JPEG)
			return "image/jpeg";
		else if(imageType == ATOM_IMAGE_BMP)
			return "image/bmp";
		else if(imageType == ATOM_IMAGE_PNG)
			return "image/png";
		else if(imageType == ATOM_IMAGE_GIF)
			return "image/gif";
		else if(imageType == ATOM_IMAGE_WBMP)
			return "image/vnd.wap.wbmp";
		else if(imageType == ATOM_IMAGE_AGIF)
			return "image/gif";
		else {
			return "unknown";
		}
	}

	/**
	 * convert JPEG File to AtomJPEG File
	 * @param srcName input path
	 * @param dstName output path
	 * @param ao atom option. if null, works default mode.
	 * @return size of AtomJPEG if success, or -1 on error.
	 */
	public static int convertJpegToAtomJPEG(String srcName, String dstName, AtomOptions ao) {
		
		String imageType = "unknown";
		int resolution[] = new int[2];

		if( srcName == null || dstName == null ) {
			Log.e(ATOM_UTILS_TAG, "encodeAtomJPEG return error : srcName = " + srcName + ", dstName = " + dstName);
			return -1;
		}

		if(ao == null) {
			ao = new AtomOptions();
		}

		if( ao.width <= 0 || ao.height <= 0 ) {
			if(!(ao.width == 0 && ao.height == 0)) {	
				Log.e(ATOM_UTILS_TAG, "dstWidth & dstHeight must be greater then Zero");
				return -1;
			}
		}
		
		imageType = getMimeType(srcName, resolution);
		if(imageType.compareTo("image/jpeg") == 0 ||
				imageType.compareTo("image/png") == 0 ||
				imageType.compareTo("image/gif") == 0 ||
				imageType.compareTo("image/BMP") == 0) {
			
		}

		else {
			return -1;
		}
		
		long currentUsage = resolution[0] * resolution[1];
		
		while(memUsage.get() > memoryMaxLimit || nInstances.get() > maxInstances ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		memUsage.addAndGet(currentUsage);
		nInstances.incrementAndGet();

	//	long nowUsage = memUsage.addAndGet(currentUsage);
	//	long nInst = nInstances.incrementAndGet();
		
	//	System.out.print("MEM USAGE : " + nowUsage/1024 + " INSTANCES : " + nInst + " : ");
		
		int ret = QrConvertJpegToAtomJPEGF2F(srcName, dstName, ao);
		
		memUsage.addAndGet(-currentUsage);
		nInstances.decrementAndGet();
		
		return ret;
	}
	/**
	 * convert JPEG File to AtomJPEG byte array
	 * @param srcName input path
	 * @param dstBuffer output byte array. must be allocated by caller. (recommended size is larger than width * height * 2) 
	 * @param ao atom option. if null, works default mode.
	 * @return size of AtomJPEG if success, or -1 on error.
	 */
	public static int convertJpegToAtomJPEG(String srcName, byte[] dstBuffer, AtomOptions ao) {
		String imageType = "unknown";
		int resolution[] = new int[2];

		if( srcName == null || dstBuffer == null ) {
			Log.e(ATOM_UTILS_TAG, "encodeAtomJPEG return error : srcName = " + srcName + ", dstName = " + dstBuffer);
			return -1;
		}

		if(ao == null) {
			ao = new AtomOptions();
		}

		if( ao.width <= 0 || ao.height <= 0 ) {
			if(!(ao.width == 0 && ao.height == 0))
			{	
				Log.e(ATOM_UTILS_TAG, "dstWidth & dstHeight must be greater then Zero");
				return -1;
			}
		}

		imageType = getMimeType(srcName, resolution);

		if(imageType != "image/jpeg") {
			return -1;
		}
		
		long currentUsage = resolution[0] * resolution[1];
		long totalUsage = memUsage.get(); 
	
		//if(false)
		while(totalUsage > memoryMaxLimit) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			totalUsage = memUsage.get();
		}
		
		memUsage.addAndGet(currentUsage);
		nInstances.incrementAndGet();
		
	//	System.out.print("MEM USAGE : " + nowUsage/1024 + " INSTANCES : " + nInst + " : ");
		
		int ret = QrConvertJpegToAtomJPEGF2B(srcName, dstBuffer, dstBuffer.length, ao);
		
		memUsage.addAndGet(-currentUsage);
		nInstances.decrementAndGet();
		
		return ret; 	
	}

	/**
	 * convert JPEG byte array to AtomJPEG byte array
	 * @param jpegStream source jpeg stream
	 * @param dstBuffer output byte array. must be allocated by caller. (recommended size is larger than width * height * 2) 
	 * @param ao atom option. if null, works default mode.
	 * @return size of AtomJPEG if success, or -1 on error.
	 */
	public static int convertJpegToAtomJPEG(byte[] jpegStream, byte[] dstBuffer, AtomOptions ao) {

		if(jpegStream == null || dstBuffer == null) {
			Log.e(ATOM_UTILS_TAG, "encodeAtomJPEG return error : jpegStream = " + jpegStream +  ", dstBuffer = " + dstBuffer);
			return -1;
		}

		if(ao == null) {
			ao = new AtomOptions();
		}

		if( ao.width <= 0 || ao.height <= 0 ) {
			if(!(ao.width == 0 && ao.height == 0)) {	
				Log.e(ATOM_UTILS_TAG, "dstWidth & dstHeight must be greater then Zero");
				return -1;
			}
		}

		return QrConvertJpegToAtomJPEGS2B(jpegStream, jpegStream.length, dstBuffer, dstBuffer.length, ao);
	}

	/**
	 * convert JPEG byte array to AtomJPEG byte array
	 * @param jpegStream source jpeg stream
	 * @param dstName output path
	 * @param ao atom option. if null, works default mode.
	 * @return size of AtomJPEG if success, or -1 on error.
	 */
	public static int convertJpegToAtomJPEG(byte[] jpegStream, String dstName, AtomOptions ao) {

		String imageType = "unknown";
		int resolution[] = new int[2];
		
		if(jpegStream == null || dstName == null) {
			Log.e(ATOM_UTILS_TAG, "encodeAtomJPEG return error : jpegStream = " + jpegStream +  ", dstName = " + dstName);
			return -1;
		}

		if(ao == null) {
			ao = new AtomOptions();
		}

		if( ao.width <= 0 || ao.height <= 0 ) {
			if(!(ao.width == 0 && ao.height == 0)) {	
				Log.e(ATOM_UTILS_TAG, "dstWidth & dstHeight must be greater then Zero");
				return -1;
			}
		}
		
		resolution[0] = resolution[1] = 1024;
		
		if(ao.width > 0)
			resolution[0] = ao.width;
		
		if(ao.height > 0)
			resolution[1] = ao.height;
		
		

		long currentUsage = resolution[0] * resolution[1];
		long totalUsage = memUsage.get(); 
	
		//if(false)
		while(totalUsage > memoryMaxLimit) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			totalUsage = memUsage.get();
		}
		
		memUsage.addAndGet(currentUsage);
		nInstances.incrementAndGet();
		
	//	System.out.print("MEM USAGE : " + nowUsage/1024 + " INSTANCES : " + nInst + " : ");
		
		int ret = QrConvertJpegToAtomJPEGS2F(jpegStream, jpegStream.length, dstName, ao);
		
		memUsage.addAndGet(-currentUsage);
		nInstances.decrementAndGet();
		return ret;
	}


	/**
	 * Resize or Re-Compress image 
	 * @param inputPath 	input image path (include directory)
	 * @param outputPath 	output path (include directory, cannot same input path)
	 * @param width 		desired width
	 * @param height 		desired height
	 * @param preset 		options. if null, works default mode.
	 * @return ConversionInfo include result size(error code), input size, error message.
	 */
	public static ConversionInfo resizeImage(String inputPath, String outputPath, int width, int height) 
	{
		ConversionInfo ci = new ConversionInfo();

		if(inputPath == null || outputPath == null) {
			ci.resultSize = ATOM_ERR_INVALID_INPUT_PARAMETER;
			ci.errorMessage = errorMessages[-ATOM_ERR_INVALID_INPUT_PARAMETER];
			return ci;
		}

		if(inputPath.compareTo(outputPath) == 0) {
			ci.resultSize = ATOM_ERR_INVALID_INPUT_PARAMETER;
			ci.errorMessage = errorMessages[-ATOM_ERR_INVALID_INPUT_PARAMETER];
			return ci;
		}

		if(width <= 0 || height <= 0) {
			if(!(width == 0 && height == 0)) {
				ci.resultSize = ATOM_ERR_INVALID_INPUT_RESOLUTION;
				ci.errorMessage = errorMessages[-ATOM_ERR_INVALID_INPUT_RESOLUTION];
				return ci;
			}
		}

		File inFile = new File(inputPath);
		if(inFile.exists() && inFile.canRead()) {
			ci.inputSize = inFile.length();
		} else {
			ci.resultSize = ATOM_ERR_FAIL_TO_OPEN_INPUT_FILE;
			ci.errorMessage = errorMessages[-ATOM_ERR_FAIL_TO_OPEN_INPUT_FILE];
			return ci;
		}

		File outFile = new File(outputPath);
		try {
			if(outFile.createNewFile() == true) {
				//create success
				outFile.delete();

			} else {
				//create success
				outFile.delete();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			if(debugMode)
				e1.printStackTrace();

			ci.resultSize = ATOM_ERR_FAIL_TO_OPEN_OUTPUT_FILE;
			ci.errorMessage = errorMessages[-ATOM_ERR_FAIL_TO_OPEN_OUTPUT_FILE];
			return ci;
		}

		try {
			ci.resultSize = recompressJpeg(inputPath, outputPath, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(debugMode) 
				e.printStackTrace();
			ci.errorMessage = e.getMessage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			if(debugMode) 
				e.printStackTrace();
			ci.errorMessage = e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(debugMode)
				e.printStackTrace();
			ci.errorMessage = e.getMessage();
		}

		if(ci.resultSize == ATOM_ERR_UNKNOWN_FORMAT) {
			ci.errorMessage = errorMessages[-ATOM_ERR_UNKNOWN_FORMAT];
		}

		if(ci.resultSize <= 0) {
			if(ci.errorMessage == null) {
				int idx = (int) -ci.resultSize;

				if(idx > errorMessages.length)
					ci.errorMessage = "Unknown Error";
				else
					ci.errorMessage = errorMessages[idx];
			}
		}
		else {
			ci.errorMessage = "Conversion Success";
		}

		return ci;	
	}
	/**
	 * re-compress JPEG to AtomJPEG.
	 * @param inputPath input JPEG path (include directory)
	 * @param outputPath output AtomJPEG path (include directory, cannot same input path)
	 * @param width width of output AtomJPEG
	 * @param height heigt of output AtomJPEG. (if width & height are both 0, then use input's width & height)
	 * @param preset options. if null, , works default mode.
	 * @return if success returns size of AtomJPEG, or -1 on error.
	 * @throws Exception 
	 * @throws IM4JavaException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InfoException 
	 */
	private static int recompressJpeg(String inputPath, String outputPath, int width, int height) throws IOException, InterruptedException, Exception {
		int ret = 0;

		if(loadLib == false) {
			return 0;
		}

		if(inputPath == null || outputPath == null)
			return -1;

		int imageType = QrGetImageType(inputPath);

		Log.d(ATOM_UTILS_TAG, "image type : " + imageType);

		//Resize Image 
		if(imageType == ATOM_IMAGE_JPEG ) {
			//JPEG AtomJPEG
			//	ret = lguConvertAtomJPEG(inputPath, outputPath, width, height);	
		} else {
			//Others : use ImageMagicK

		
		}

		if(ret == 0) {
			ret = ATOM_ERR_CONVERSION_FAIL;
		}


		return ret;
	}

	

	// Native Functions
	private static native void setJniEnvironment();
	
	private static native int QrGetImageType(String srcName);
	private static native int QrGetImageInfo(String srcName, int[] resolution);
	private static native int QrGetJPEGInfo(String srcName);
	private static native double QrGetQualityOfJPEG(String srcName);

	private static native int QrConvertJpegToAtomJPEGF2F(String inputPath, String outputPath, AtomOptions ao);
	private static native int QrConvertJpegToAtomJPEGF2B(String inputPath, byte[] outputBuffer, int outputBufferSize, AtomOptions ao);

	private static native int QrConvertJpegToAtomJPEGS2B(byte[] jpegStream, int jpegStreamSize, byte[] outputBuffer, int outputBufferSize, AtomOptions ao);
	private static native int QrConvertJpegToAtomJPEGS2F(byte[] jpegStream, int jpegStreamSize, String outputPath, AtomOptions ao);
}

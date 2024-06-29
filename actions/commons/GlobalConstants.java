package commons;

import java.io.File;

public class GlobalConstants {
	public static final String NORMAL_URL = "https://demo.nopcommerce.com";

	public static long LONG_TIMEOUT = 30;
	public static long SHORT_TIMEOUT = 10;
	public static long RETRY_TEST_FAIL = 3;

	public static  String OS_NAME = System.getProperty("os.name");
	public static  String JAVA_VERSION = System.getProperty("java.version");

	public static  String PROJECT_PATH = System.getProperty("user.dir");
	public static  String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static  String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static  String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static  String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static  String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static  String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages"+ File.separator;
}
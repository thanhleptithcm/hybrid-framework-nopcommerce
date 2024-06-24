package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
//	private String projectPath = System.getProperty("user.dir");
	
	protected WebDriver getBrowserDriver(String browserName) {
		WebDriver driver;
		if(browserName.equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			WebDriverManager.firefoxdriver().setup(); // Dùng thư viện libWebDriverManager
			driver = new FirefoxDriver();
		} else if(browserName.equals("h_firefox")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			WebDriverManager.firefoxdriver().setup(); // Dùng thư viện libWebDriverManager
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if(browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			WebDriverManager.chromedriver().setup(); // Dùng thư viện libWebDriverManager
			driver = new ChromeDriver();	
		} else if(browserName.equals("h_chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			WebDriverManager.chromedriver().setup(); // Dùng thư viện libWebDriverManager
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);	
		}	else if(browserName.equals("edge")) {
//			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
			WebDriverManager.edgedriver().setup(); // Dùng thư viện libWebDriverManager
			driver = new EdgeDriver();
		}	else if(browserName.equals("opera")) {
//			System.setProperty("webdriver.opera.driver", projectPath + "/browserDrivers/operadriver");
			WebDriverManager.operadriver().setup(); // Dùng thư viện libWebDriverManager
			driver = new EdgeDriver();
		}	else if(browserName.equals("coccoc")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			WebDriverManager.operadriver().driverVersion("version hiện tại - 5 hoặc 6").setup(); // Dùng thư viện libWebDriverManager
			
			ChromeOptions options = new ChromeOptions();
			options.setBinary("Link broser của cốc cốc"); //kèm vs version driver chrome trừ đi 5 hoặc 6
			
			driver = new ChromeDriver(options);	
		}	else if(browserName.equals("brave")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			WebDriverManager.operadriver().driverVersion("version hiện tại").setup(); // Dùng thư viện libWebDriverManager
			
			ChromeOptions options = new ChromeOptions();
			options.setBinary("Link broser của brave"); //kèm vs version driver chrome
			
			driver = new ChromeDriver(options);	
		} else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
}

package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Register_Empty_Data() {

	}

	@Test
	public void TC_02_Register_Invalid_Email() {

	}

	@Test
	public void TC_03_Register_Existing_Email() {

	}

	@Test
	public void TC_04_Register_Password_Less_Than_6_Chars() {

	}

	@Test
	public void TC_05_Register_Invalid_Confirm_Password() {

	}

	@Test
	public void TC_06_Register_Success() {

	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

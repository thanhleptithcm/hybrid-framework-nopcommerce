package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_01_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;
	
	BasePage basePage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		basePage = new BasePage();
		
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		sleepInSecond(2);
		
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		sleepInSecond(2);
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}
	

	@Test 
	public void TC_02_Register_Invalid_Email() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		sleepInSecond(2);
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Thanh");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Le");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", "123@123@123");
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
			
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		sleepInSecond(2);
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Please enter a valid email address.");
	}
	
	@Test
	public void TC_03_Register_Success() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		sleepInSecond(2);
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Thanh");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Le");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
			
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		sleepInSecond(2);
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");

		basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
		basePage.clickToElement(driver, "//a[@class='ico-logout']");
	}
	

	@Test
	public void TC_04_Register_Existing_Email() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		sleepInSecond(2);
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Thanh");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Le");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
			
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		sleepInSecond(2);
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
	}
	

	@Test
	public void TC_05_Register_Password_Less_Than_6_Char() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		sleepInSecond(2);
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Thanh");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Le");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
			
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		sleepInSecond(2);
		
		Assert.assertEquals(basePage.getElementText(driver,"//input[@id='Password']//following-sibling::span[@data-valmsg-for='Password']"), "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
	}
	

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		sleepInSecond(2);
		
		basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "Thanh");
		basePage.sendKeyToElement(driver, "//input[@id='LastName']", "Le");
		basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
		basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123654");
			
		basePage.waitForElementClickable(driver, "//button[@id='register-button']");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		sleepInSecond(2);
		
		Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}

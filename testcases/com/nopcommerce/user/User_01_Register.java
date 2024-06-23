package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class User_01_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password;
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		
		firstName = "Le";
		lastName = "Thanh";
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		password = "123456";
		
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		System.out.println("Register 01 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register 01 - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register 01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "Password is required.");
	}
	
	@Test 
	public void TC_02_Register_Invalid_Email() {
		System.out.println("Register 02 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register 02 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox("123@123@123");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		System.out.println("Register 02 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register 02 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Please enter a valid email address.");
	}
	
	@Test
	public void TC_03_Register_Success() {
		System.out.println("Register 03 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register 03 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		System.out.println("Register 03 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register 03 - Step 04: Verify message success displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register 03 - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
	}
	

	@Test
	public void TC_04_Register_Existing_Email() {
		System.out.println("Register 04 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register 04 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
			
		System.out.println("Register 04 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register 04 - Step 04: Verify error existing email message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}
	

	@Test
	public void TC_05_Register_Password_Less_Than_6_Char() {
		System.out.println("Register 05 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register 05 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox("123");
		registerPage.inputToConfirmPasswordTextBox("123");
		
		System.out.println("Register 05 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register 05 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
	}
	

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		System.out.println("Register 06 - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Register 06 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox("123456");
		registerPage.inputToConfirmPasswordTextBox("123654");
		
		System.out.println("Register 06 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register 06 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "The password and confirmation password do not match.");
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

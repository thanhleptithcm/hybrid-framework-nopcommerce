package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class User_02_Login extends BaseTest {
	WebDriver driver;
	
	String firstName, lastName, emailAddress, password;
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	
	@Parameters("browser")
    @BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		loginPage = new LoginPageObject(driver);

		firstName = "Le";
		lastName = "Thanh";
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		password = "123456";
		
		System.out.println("Pre-Condition - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		System.out.println("Pre-Condition - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		System.out.println("Pre-Condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Pre-Condition - Step 04: Verify message success displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-Condition - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
	}
	
	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login 01 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login 01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login 02 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login 02 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox("123@123@123");
		
		System.out.println("Login 02 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 02 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter a valid email address.");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		System.out.println("Login 03 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login 03 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox("test99@gmail.com");
		loginPage.inputToPasswordTextBox("123456");
		
		System.out.println("Login 03 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 03 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Password_Empty() {
		System.out.println("Login 04 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login 04 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox("");
		
		System.out.println("Login 04 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 04 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("Login 05 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login 05 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox("456123");
		
		System.out.println("Login 05 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 05 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password() {
		System.out.println("Login 06 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login 06 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		
		System.out.println("Login 06 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 06 - Step 04: Verify My Account Displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		System.out.println("Login 06 - Step 05: Click to My Account link");
		homePage.clickToMyAccountLink();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}	
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}

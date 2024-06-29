package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneraterManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.RegisterPageObject;

public class User_02_Login extends BaseTest {
	WebDriver driver;
	
	String firstName, lastName, emailAddress, password;
	
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyAccountPageObject myAccountPage;
	
	@Parameters({"browser", "url"})
    @BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneraterManager.getHomePageObject(driver);

		firstName = "Le";
		lastName = "Thanh";
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		password = "123456";
		
		log.info("Pre-Condition - Step 01: Click to Register link");
		RegisterPageObject registerPage = homePage.clickToRegisterLink();
		
		log.info("Pre-Condition - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		log.info("Pre-Condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-Condition - Step 04: Verify message success displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Pre-Condition - Step 05: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
	}
	
	@Test
	public void Login_01_Empty_Data() {
		log.info("Login 01 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 01 - Step 03: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		log.info("Login 02 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 02 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox("123@123@123");
		
		log.info("Login 02 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 02 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter a valid email address.");
	}
	
	@Test
	public void Login_03_Email_Not_Found() {
		log.info("Login 03 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 03 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox("test99@gmail.com");
		loginPage.inputToPasswordTextBox("123456");
		
		log.info("Login 03 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 03 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Password_Empty() {
		log.info("Login 04 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 04 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox("");
		
		log.info("Login 04 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 04 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		log.info("Login 05 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 05 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox("456123");
		
		log.info("Login 05 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 05 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password() {
		log.info("Login 06 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Login 06 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		
		log.info("Login 06 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		log.info("Login 06 - Step 04: Verify My Account Displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		log.info("Login 06 - Step 05: Click to My Account link");
		myAccountPage = homePage.clickToMyAccountLink();
		
		log.info("Login 05 - Step 04: Verify My Account displayed");
		verifyEquals(myAccountPage.getTitleHeader(), "My account - Customer info");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

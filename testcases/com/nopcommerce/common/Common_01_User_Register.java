package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneraterManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Common_01_User_Register extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName;
	public static String password, emailAddress;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	
//	public static Set<Cookie> loggedCookies;
	
	@Parameters({"browser", "url"})
    @BeforeTest
	public void beforeTest(String browserName, String url) {
		System.out.println("Vào beforeTest");
		driver = getBrowserDriver(browserName, url);
	 	homePage = PageGeneraterManager.getHomePageObject(driver);

		firstName = "Le";
		lastName = "Thanh";
		emailAddress = "thanh" + getRandomNumber() + "@gmail.vn";
		password = "123456";
		
		System.out.println("emailAddress: " + emailAddress);
		
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToLogoutLink(driver);
		loginPage = homePage.clickToLoginLink();
		
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		
		loginPage.clickToLoginButton();
		
//		loggedCookies = loginPage.getAllCookies(driver);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		System.out.println("Vào afterTest");
		closeBrowserAndDriver();
	}

}

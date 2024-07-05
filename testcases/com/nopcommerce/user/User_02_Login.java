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
import reportConfig.ExtentTestManager;
import java.lang.reflect.Method;
import com.aventstack.extentreports.Status;

public class User_02_Login extends BaseTest {
	private WebDriver driver;
	
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	
	@Parameters({"browser", "url"})
    @BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneraterManager.getHomePageObject(driver);
	}
	
	@Test
	public void Login_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login_01_Empty_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Login 01 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 01 - Step 02: Click to Login button");
		loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login_02_Invalid_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Login 02 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 02 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox("123@123@123");
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 02 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 02 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter a valid email address.");
	}
	
	@Test
	public void Login_03_Email_Not_Found(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login_03_Email_Not_Found");
		ExtentTestManager.getTest().log(Status.INFO, "Login 03 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 03 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox("test99@gmail.com");
		loginPage.inputToPasswordTextBox("123456");
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 03 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 03 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Password_Empty(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login_04_Existing_Email_Password_Empty");
		ExtentTestManager.getTest().log(Status.INFO, "Login 04 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 04 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(User_01_Register.emailAddress);
		loginPage.inputToPasswordTextBox("");
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 04 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 04 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Existing_Email_Incorrect_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login_05_Existing_Email_Incorrect_Password");
		ExtentTestManager.getTest().log(Status.INFO, "Login 05 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 05 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(User_01_Register.emailAddress);
		loginPage.inputToPasswordTextBox("456123");
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 05 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 05 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login_06_Valid_Email_Password");
		ExtentTestManager.getTest().log(Status.INFO, "Login 06 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 06 - Step 02: Input to required fields");
		loginPage.inputToEmailTextBox(User_01_Register.emailAddress);
		loginPage.inputToPasswordTextBox(User_01_Register.password);
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 06 - Step 03: Click to Login button");
		loginPage.clickToLoginButton();
		homePage = PageGeneraterManager.getHomePageObject(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 06 - Step 04: Verify My Account Displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 06 - Step 05: Click to My Account link");
		myAccountPage = homePage.clickToMyAccountLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login 05 - Step 04: Verify My Account displayed");
		Assert.assertEquals(myAccountPage.getTitleHeader(driver), "My account - Customer info");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}

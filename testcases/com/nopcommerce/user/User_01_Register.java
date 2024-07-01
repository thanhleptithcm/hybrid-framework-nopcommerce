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
import pageObjects.RegisterPageObject;
import reportConfig.ExtentTestManager;
import java.lang.reflect.Method;
import com.aventstack.extentreports.Status;

public class User_01_Register extends BaseTest {
	WebDriver driver;
	String firstName, lastName, emailAddress, password;
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	
	
	@Parameters({"browser", "url"})
    @BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
	 	homePage = PageGeneraterManager.getHomePageObject(driver);

		firstName = "Le";
		lastName = "Thanh";
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		password = "123456";
	}

	@Test
	public void TC_01_Register_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_Register_Empty_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Step 02: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "Password is required.");
	}
	
	@Test 
	public void TC_02_Register_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_02_Register_Invalid_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox("123@123@123");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Please enter a valid email address.");
	}
	
	@Test
	public void TC_03_Register_Success(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_03_Register_Success");
		ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Step 04: Verify message success displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
	}
	

	@Test
	public void TC_04_Register_Existing_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_04_Register_Existing_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
			
		ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Step 04: Verify error existing email message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}
	

	@Test
	public void TC_05_Register_Password_Less_Than_6_Char(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_05_Register_Password_Less_Than_6_Char");
		ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox("123");
		registerPage.inputToConfirmPasswordTextBox("123");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
	}
	

	@Test
	public void TC_06_Register_Invalid_Confirm_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_06_Register_Invalid_Confirm_Password");
		ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox("123456");
		registerPage.inputToConfirmPasswordTextBox("123654");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

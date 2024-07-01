package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneraterManager;
import pageObjects.AddressesPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.RegisterPageObject;
import reportConfig.ExtentTestManager;
import java.lang.reflect.Method;
import com.aventstack.extentreports.Status;

public class User_03_My_Account extends BaseTest {
	WebDriver driver;
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	MyAccountPageObject myAccountPage;
	CustomerInfoPageObject customerInfoPage;
	AddressesPageObject addressesPage;
	
	String firstName, lastName, emailAddress, password;
	String firstNameNew, lastNameNew, emailAddressNew, passwordNew, companyNameNew, dayNew, monthNew, yearNew;
	
	@Parameters({"browser", "url"})
    @BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		
	 	homePage = PageGeneraterManager.getHomePageObject(driver);

		firstName = "Le";
		lastName = "Thanh";
		emailAddress = "thanh" + getRandomNumber() + "@gmail.vn";
		password = "123456";
		
		firstNameNew = "Thanh";
		lastNameNew = "Le";
		dayNew = "20";
		monthNew = "January";
		yearNew = "1996";
		emailAddressNew = "le" + getRandomNumber() + "@gmail.vn";
		passwordNew = "123456";
		companyNameNew = "CafeLand";
		
		ExtentTestManager.startTest("Pre-Condition", "Pre-Condition Register An Account");
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 01: Click to Register link");
		RegisterPageObject registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 04: Verify message success displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 05: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 06: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 07: Input to required fields");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 08: Click to Login button");
		loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 09: Verify My Account Displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 10: Click to My Account link");
		myAccountPage = homePage.clickToMyAccountLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Pre-Condition - Step 11: Verify My Account displayed");
		Assert.assertEquals(myAccountPage.getTitleHeader(), "My account - Customer info");
	}
	
	@Test
	public void My_Account_01_Update_Info_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_01_Update_Info_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 01: Click to Customer Info link");
		customerInfoPage = (CustomerInfoPageObject) myAccountPage.openPageAtMyAccountByName(driver, "Customer info");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 02: Input to required fields");
		customerInfoPage.clickToFemaleRadioButton();
		
		customerInfoPage.inputToFirstNameTextBox(firstNameNew);
		customerInfoPage.inputToLastNameTextBox(lastNameNew);
		
		customerInfoPage.chooseDayDropdown(dayNew);
		customerInfoPage.chooseMonthDropdown(monthNew);
		customerInfoPage.chooseYearDropdown(yearNew);
		
		customerInfoPage.inputToEmailTextBox(emailAddressNew);
		customerInfoPage.inputToCompanyNameTextBox(companyNameNew);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 03: Click to Save button");
		customerInfoPage.clickToSaveButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 04: Verify data update");
		Assert.assertTrue(customerInfoPage.isFemaleRadioButtonSelected());
		
		Assert.assertEquals(customerInfoPage.getValueAtFirstNameTextBox(), firstNameNew);
		Assert.assertEquals(customerInfoPage.getValueAtLastNameTextBox(), lastNameNew);

		Assert.assertEquals(customerInfoPage.getValueAtDayDropdown(), dayNew);
		Assert.assertEquals(customerInfoPage.getValueAtMonthDropdown(), monthNew);
		Assert.assertEquals(customerInfoPage.getValueAtYearDropdown(), yearNew);
		
		Assert.assertEquals(customerInfoPage.getValueAtEmailTextBox(), emailAddressNew);
		Assert.assertEquals(customerInfoPage.getValueAtCompanyNameTextBox(), companyNameNew);
	}
	
	@Test
	public void My_Account_02_Add_Addresses_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_02_Add_Addresses_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "My Account 02 - Step 01: Click to Addresses link");
		addressesPage = (AddressesPageObject) customerInfoPage.openPageAtMyAccountByName(driver, "Addresses");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

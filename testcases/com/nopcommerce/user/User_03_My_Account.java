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
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import reportConfig.ExtentTestManager;
import java.lang.reflect.Method;
import com.aventstack.extentreports.Status;
import com.nopcommerce.common.Common_01_User_Register;

public class User_03_My_Account extends BaseTest {
	private WebDriver driver;
	
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressesPageObject addressesPage;
	private ChangePasswordPageObject changePasswordPage;
	
	private String firstName, lastName, fullName, companyName, day, month, year;
	private String city, address1, address2, postalCode, phoneNumber, faxNumber, country, passwordNew;
	
	@Parameters({"browser", "url"})
    @BeforeClass
	public void beforeClass(String browserName, String url) {
		System.out.println("Vào beforeClass");
		driver = getBrowserDriver(browserName, url);
	 	homePage = PageGeneraterManager.getHomePageObject(driver);
	 	
	 	firstName = "Thanh";
		lastName = "Le";
		fullName = firstName + " " + lastName;
		day = "20";
		month = "January";
		year = "1996";
		companyName = "CafeLand";
		
		city = "Ho Chi Minh";
		address1 = "123 Le Hong Phong";
		address2 = "456 Nguyen Van Troi";
		postalCode = "700000";
		phoneNumber = "0237191361";
		faxNumber = "0238137492";
		country = "Viet Nam";
		
		passwordNew = "111111";
	 	
	 	homePage.setCookies(driver, Common_01_User_Register.loggedCookies);
	 	homePage.refreshCurrentPage(driver);
	 	
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		myAccountPage = homePage.clickToMyAccountLink();
		Assert.assertEquals(myAccountPage.getTitleHeader(), "My account - Customer info");
	}
	
	@Test
	public void My_Account_01_Update_Info_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_01_Update_Info_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 01: Click to Customer Info link");
		customerInfoPage = (CustomerInfoPageObject) myAccountPage.openPageAtMyAccountByName(driver, "Customer info");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 02: Input to required fields");
		customerInfoPage.clickToFemaleRadioButton();
		
		customerInfoPage.inputToTextboxByID(driver, "FirstName", firstName);
		customerInfoPage.inputToTextboxByID(driver, "LastName", lastName);
		
		customerInfoPage.chooseDayDropdown(day);
		customerInfoPage.chooseMonthDropdown(month);
		customerInfoPage.chooseYearDropdown(year);
		
		customerInfoPage.inputToTextboxByID(driver, "Email", Common_01_User_Register.emailAddress);
		customerInfoPage.inputToTextboxByID(driver, "Company", companyName);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 03: Click to Save button");
		customerInfoPage.clickToSaveButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 01 - Step 04: Verify data update");
		Assert.assertTrue(customerInfoPage.isFemaleRadioButtonSelected());
		
		Assert.assertEquals(customerInfoPage.getValueAtFirstNameTextBox(), firstName);
		Assert.assertEquals(customerInfoPage.getValueAtLastNameTextBox(), lastName);

		Assert.assertEquals(customerInfoPage.getValueAtDayDropdown(), day);
		Assert.assertEquals(customerInfoPage.getValueAtMonthDropdown(), month);
		Assert.assertEquals(customerInfoPage.getValueAtYearDropdown(), year);
		
		Assert.assertEquals(customerInfoPage.getValueAtEmailTextBox(), Common_01_User_Register.emailAddress);
		Assert.assertEquals(customerInfoPage.getValueAtCompanyNameTextBox(), companyName);
	}
	
	@Test
	public void My_Account_02_Add_Addresses_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_02_Add_Addresses_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "My Account 02 - Step 01: Click to Addresses link");
		addressesPage = (AddressesPageObject) customerInfoPage.openPageAtMyAccountByName(driver, "Addresses");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 02 - Step 02: Click to Add new button");
		addressesPage.clickToButtonByText(driver, "Add new");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 02 - Step 03: Input to required fields");
		addressesPage.inputToTextboxByID(driver, "Address_FirstName", firstName);
		addressesPage.inputToTextboxByID(driver, "Address_LastName", lastName);
		addressesPage.inputToTextboxByID(driver, "Address_Email", Common_01_User_Register.emailAddress);
		addressesPage.inputToTextboxByID(driver, "Address_Company", companyName);
		addressesPage.selectToDropdownById(driver, "Address_CountryId", country);
		addressesPage.inputToTextboxByID(driver, "Address_City", city);
		addressesPage.inputToTextboxByID(driver, "Address_Address1", address1);
		addressesPage.inputToTextboxByID(driver, "Address_Address2", address2);
		addressesPage.inputToTextboxByID(driver, "Address_ZipPostalCode", postalCode);
		addressesPage.inputToTextboxByID(driver, "Address_PhoneNumber", phoneNumber);
		addressesPage.inputToTextboxByID(driver, "Address_FaxNumber", faxNumber);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 02 - Step 04: Click to Save button");
		addressesPage.clickToButtonByText(driver, "Save");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 02 - Step 05: Verify data update");
		Assert.assertEquals(addressesPage.getValueTitleAtAddresses(), fullName);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "name"), fullName);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "email"), "Email: " + Common_01_User_Register.emailAddress);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "company"), companyName);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "country"), country);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "city"), city);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "address1"), address1);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "address2"), address2);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "zippostalcode"), postalCode);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "phone"), "Phone number: " +  phoneNumber);
		Assert.assertEquals(addressesPage.getValueAtAddressesByClass(driver, "fax"), "Fax number: " + faxNumber);
	}
	
	@Test
	public void My_Account_03_Change_Password_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_03_Change_Password_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 01: Click to Change password link");
		changePasswordPage = (ChangePasswordPageObject) customerInfoPage.openPageAtMyAccountByName(driver, "Change password");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 02: Input to required fields");
		changePasswordPage.inputToTextboxByID(driver, "OldPassword", Common_01_User_Register.password);
		changePasswordPage.inputToTextboxByID(driver, "NewPassword", passwordNew);
		changePasswordPage.inputToTextboxByID(driver, "ConfirmNewPassword", passwordNew);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 03: Click to Change password button");
		changePasswordPage.clickToButtonByText(driver, "Change password");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 04: Verify Change password success");
		Assert.assertEquals(changePasswordPage.getPasswordWasChangedAtTextBox(), "Password was changed");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 05: Click to Close button");
		changePasswordPage.clickToCloseBarNotification();
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 06: Click to Logout link");
		homePage = changePasswordPage.clickToLogoutLink(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 07: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 08: Input to Email: " + Common_01_User_Register.emailAddress);
		loginPage.inputToEmailTextBox(Common_01_User_Register.emailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 09: Input to Password Old: " + Common_01_User_Register.password);
		loginPage.inputToPasswordTextBox(Common_01_User_Register.password);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 10: Click to Login button");
		loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 11: Verify error message login failed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 12: Input to Email: " + Common_01_User_Register.emailAddress);
		loginPage.inputToEmailTextBox(Common_01_User_Register.emailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 13: Input to Password New: " + passwordNew);
		loginPage.inputToPasswordTextBox(passwordNew);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 14: Click to Login button");
		loginPage.clickToLoginButton();
		homePage = PageGeneraterManager.getHomePageObject(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "My Account 03 - Step 15: Verify My Account Displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Vào afterClass");
		driver.quit();
	}
}

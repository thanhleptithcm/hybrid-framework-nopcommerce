package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.google.common.base.Verify;
import com.nopcommerce.common.Common_01_User_Register;

import commons.BaseTest;
import commons.PageGeneraterManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.SearchPageObject;
import reportConfig.ExtentTestManager;

public class User_04_Search extends BaseTest {
	private WebDriver driver;
	
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private SearchPageObject searchPage;
	
	private String emailAddress, password;
	
	@Parameters({"browser", "url"})
    @BeforeClass
	public void beforeClass(String browserName, String url) {
		System.out.println("Vào beforeClass");
		driver = getBrowserDriver(browserName, url);
	 	homePage = PageGeneraterManager.getHomePageObject(driver);
	 	
		
		emailAddress = Common_01_User_Register.emailAddress;
		password = Common_01_User_Register.password;
		
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		searchPage = homePage.clickToSearchLink();
		Assert.assertEquals(searchPage.getTitleHeader(driver), "Search");
	}
	
	@Test
	public void Search_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_01_Empty_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 01: Enter to Search empty data");
		searchPage.inputToSearchTextBox("");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 02: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 03: Verify error message");
		Assert.assertEquals(searchPage.getWarningTextBox(), "Search term minimum length is 3 characters");
	}

	@Test
	public void Search_02_Data_Not_Exsit(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_02_Data_Not_Exsit");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02 - Step 01: Enter to Search is: Macbook Pro 2050");
		searchPage.inputToSearchTextBox("Macbook Pro 2050");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 02 - Step 02: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 02 - Step 03: Verify error message");
		Assert.assertEquals(searchPage.getNoResultTextBox(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_03_Search_Name_Product_Relative(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_03_Search_Name_Product_Relative");
		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 01: Enter to Search is: Lenovo");
		searchPage.inputToSearchTextBox("Lenovo");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 02: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 03: Verify to total product visible");
		int length = searchPage.getTotalItemProductResult();
		Assert.assertEquals(length, 2);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Step 04: Verify to title product");
		for(int i = 1; i <= length; i++) {
			Assert.assertTrue(searchPage.getTitleAtItem(i).contains("Lenovo"));
		}
	}

	@Test
	public void Search_04_Search_Name_Product_Absolute(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_04_Search_Name_Product_Absolute");
		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Step 01: Enter to Search is: Lenovo Thinkpad X1 Carbon Laptop");
		searchPage.inputToSearchTextBox("Lenovo Thinkpad X1 Carbon Laptop");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Step 02: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Step 03: Verify to total product visible");
		int length = searchPage.getTotalItemProductResult();
		Assert.assertEquals(length, 1);
		
		for(int i = 1; i <= length; i++) {
			Assert.assertEquals(searchPage.getTitleAtItem(i), "Lenovo Thinkpad X1 Carbon Laptop");
		}
	}

	@Test
	public void Search_05_Advanced_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_05_Advanced_Search");
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 01: Enter to Search is: Apple Macbook Pro");
		searchPage.inputToSearchTextBox("Apple Macbook Pro");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 02: Check to Advanced Search checkbox");
		searchPage.clickToAdvancedSearchCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 03: Choose Category is: Computers");
		searchPage.chooseDynamicSearchDropdownByValue("Category:", "Computers");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 04: Check to Advanced Search checkbox");
		searchPage.unCheckToSubCategoriesCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 05: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Step 06: Verify error message");
		Assert.assertEquals(searchPage.getNoResultTextBox(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_06_Advanced_Search_Checked_Sub_Category(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_06_Advanced_Search_SUB_CATEGORY");
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 01: Enter to Search is: Apple Macbook Pro");
		searchPage.inputToSearchTextBox("Apple Macbook Pro");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 02: Check to Advanced Search checkbox");
		searchPage.clickToAdvancedSearchCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 03: Choose Category is: Computers");
		searchPage.chooseDynamicSearchDropdownByValue("Category:", "Computers");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 04: Check to Advanced Search checkbox");
		searchPage.checkToSubCategoriesCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 05: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 06: Verify to total product visible");
		int length = searchPage.getTotalItemProductResult();
		Assert.assertEquals(length, 1);
		
		for(int i = 1; i <= length; i++) {
			Assert.assertEquals(searchPage.getTitleAtItem(i), "Apple MacBook Pro 13-inch");
		}
	}

	@Test
	public void Search_07_Advanced_Search_Incorrect_Manuafaturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_07_Advanced_Search_Incorrect_Manuafaturer");
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 01: Enter to Search is: Apple Macbook Pro");
		searchPage.inputToSearchTextBox("Apple Macbook Pro");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 02: Check to Advanced Search checkbox");
		searchPage.clickToAdvancedSearchCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 03: Choose Category is: Computers");
		searchPage.chooseDynamicSearchDropdownByValue("Category:", "Computers");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 04: Check to Advanced Search checkbox");
		searchPage.checkToSubCategoriesCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 05: Choose Category is: Computers");
		searchPage.chooseDynamicSearchDropdownByValue("Manufacturer:", "HP");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 06: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 07 - Step 07: Verify error message");
		Assert.assertEquals(searchPage.getNoResultTextBox(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_08_Advanced_Search_Correct_Manuafaturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_08_Advanced_Search_Correct_Manuafaturer");
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 01: Enter to Search is: Apple Macbook Pro");
		searchPage.inputToSearchTextBox("Apple Macbook Pro");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 02: Check to Advanced Search checkbox");
		searchPage.clickToAdvancedSearchCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 03: Choose Category is: Computers");
		searchPage.chooseDynamicSearchDropdownByValue("Category:", "Computers");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 04: Check to Advanced Search checkbox");
		searchPage.checkToSubCategoriesCheckBox();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 05: Choose Category is: Computers");
		searchPage.chooseDynamicSearchDropdownByValue("Manufacturer:", "Apple");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 06: Click to Search button");
		searchPage.clickToSearchButton(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 08 - Step 07: Verify to total product visible");
		int length = searchPage.getTotalItemProductResult();
		Assert.assertEquals(length, 1);
		
		for(int i = 1; i <= length; i++) {
			Assert.assertEquals(searchPage.getTitleAtItem(i), "Apple MacBook Pro 13-inch");
		}
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Vào afterClass");
		driver.quit();
	}
}
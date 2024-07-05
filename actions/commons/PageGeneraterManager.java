package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.AddressesPageObject;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.ProductPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.SearchPageObject;

public class PageGeneraterManager {
	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static MyAccountPageObject getMyAccountPageObject(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}

	public static CustomerInfoPageObject getCustomerInfoPageObject(WebDriver driver) {
		return new CustomerInfoPageObject(driver);
	}

	public static AddressesPageObject getAddressesPageObject(WebDriver driver) {
		return new AddressesPageObject(driver);
	}
	
	public static ChangePasswordPageObject getChangePasswordPageObject(WebDriver driver) {
		return new ChangePasswordPageObject(driver);
	}

	public static ProductPageObject getProductPageObject(WebDriver driver) {
		return new ProductPageObject(driver);
	}

	public static SearchPageObject getSearchPageObject(WebDriver driver) {
		return new SearchPageObject(driver);
	}
}

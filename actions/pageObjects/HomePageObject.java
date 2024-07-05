package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver,  HomePageUI.REGISTER_LINK);
		return PageGeneraterManager.getRegisterPageObject(driver);
	}

	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver,  HomePageUI.LOGIN_LINK);
		return PageGeneraterManager.getLoginPageObject(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public MyAccountPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver,  HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneraterManager.getMyAccountPageObject(driver);
	}

	public ProductPageObject clickToItemProduct(String nameProduct) {
		waitForElementVisible(driver, HomePageUI.DYNAMIC_PRODUCT_NAME_LINK, nameProduct);
		scrollToElement(driver, HomePageUI.DYNAMIC_PRODUCT_NAME_LINK, nameProduct);
		clickToElement(driver,  HomePageUI.DYNAMIC_PRODUCT_NAME_LINK, nameProduct);
		return PageGeneraterManager.getProductPageObject(driver);
	}

	public SearchPageObject clickToSearchLink() {
		waitForElementClickable(driver, HomePageUI.DYNAMIC_FEATURE_FOOTER, "Search");
		clickToElement(driver,  HomePageUI.DYNAMIC_FEATURE_FOOTER, "Search");
		return PageGeneraterManager.getSearchPageObject(driver);
	}
}

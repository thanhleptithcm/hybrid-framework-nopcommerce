package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	private WebDriver driver;
	
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitleHeader() {
		waitForElementVisible(driver, MyAccountPageUI.TITLE_HEADER_H1);
		return getElementText(driver, MyAccountPageUI.TITLE_HEADER_H1);
	}
	
}
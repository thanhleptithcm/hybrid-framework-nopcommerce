package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.LocatorType;
import pageUIs.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	private WebDriver driver;
	
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
}
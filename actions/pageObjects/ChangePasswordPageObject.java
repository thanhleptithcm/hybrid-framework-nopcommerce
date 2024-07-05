package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class ChangePasswordPageObject extends BasePage {
	private WebDriver driver;
	
	public ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

}

package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.LocatorType;
import pageUIs.ChangePasswordPageUI;

public class ChangePasswordPageObject extends BasePage {
	private WebDriver driver;
	
	public ChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getPasswordWasChangedAtTextBox() {
		waitForElementVisible(driver, ChangePasswordPageUI.BAR_NOTIFICATION_TEXT_BOX);
		return getElementText(driver, ChangePasswordPageUI.BAR_NOTIFICATION_TEXT_BOX, LocatorType.XPATH);
	}

	public void clickToCloseBarNotification() {
		waitForElementClickable(driver, ChangePasswordPageUI.CLOSE_BAR_NOTIFICATION_SPAN);
		clickToElement(driver, ChangePasswordPageUI.CLOSE_BAR_NOTIFICATION_SPAN);
		sleepInsecond(3);
	}

}

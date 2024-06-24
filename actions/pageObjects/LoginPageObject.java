package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public void inputToEmailTextBox(String value) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXT_BOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXT_BOX, value);
	}

	public void inputToPasswordTextBox(String value) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXT_BOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXT_BOX, value);
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageLoginUnsuccessful() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
	}
}

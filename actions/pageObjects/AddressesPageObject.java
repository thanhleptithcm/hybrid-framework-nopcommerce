package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.LocatorType;
import pageUIs.AddressesPageUI;
import pageUIs.CustomerInfoUI;

public class AddressesPageObject extends BasePage {
	private WebDriver driver;
	
	public AddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getValueTitleAtAddresses() {
		waitForElementVisible(driver, AddressesPageUI.TITLE_ADDRESSES_TEXT_BOX);
		return getElementText(driver, AddressesPageUI.TITLE_ADDRESSES_TEXT_BOX, LocatorType.XPATH);
	}

}

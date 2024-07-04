package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.LocatorType;
import pageUIs.ProductPageUI;

public class ProductPageObject  extends BasePage {
	private WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddReview() {
		waitForElementClickable(driver, ProductPageUI.ADD_REVIEW_LINK, LocatorType.XPATH);
		clickToElement(driver, ProductPageUI.ADD_REVIEW_LINK, LocatorType.XPATH);
	}

	public void inputToReviewTextArea(String value) {
		waitForElementVisible(driver, ProductPageUI.REVIEW_TEXT_AREA);
		sendKeyToElement(driver, ProductPageUI.REVIEW_TEXT_AREA, value);
	}

	public void chooseRatingByRadio() {
		waitForElementClickable(driver, ProductPageUI.RATING_RADIO, LocatorType.XPATH);
		checkToDefaultCheckboxRadio(driver, ProductPageUI.RATING_RADIO, LocatorType.XPATH);
	}
}
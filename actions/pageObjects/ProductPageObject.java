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

	public void chooseRatingByRadio(String rating) {
		waitForElementClickable(driver, ProductPageUI.RATING_RADIO, rating);
		checkToDefaultCheckboxRadioByXpath(driver, ProductPageUI.RATING_RADIO, rating);
	}

	public boolean isReviewTitleDisplayed(String title) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_REVIEW_TITLE_TEXT_BOX, title);
		return isElementDisplayed(driver, ProductPageUI.DYNAMIC_REVIEW_TITLE_TEXT_BOX, title);
	}

	public String getReviewRatingAtTextBox() {
		waitForElementVisible(driver, ProductPageUI.REVIEW_RATING_TEXT_BOX);
		return getElementText(driver, ProductPageUI.REVIEW_RATING_TEXT_BOX, LocatorType.XPATH);
	}

	public boolean isReviewDescriptionDisplayed(String desc) {
		waitForElementVisible(driver, ProductPageUI.DYNAMIC_REVIEW_DESC_TEXT_BOX, desc);
		return isElementDisplayed(driver, ProductPageUI.DYNAMIC_REVIEW_DESC_TEXT_BOX, desc);
	}
	
	public void moveToListReview() {
		scrollToElement(driver, ProductPageUI.EXISTING_REVIEW_TEXT_BOX);
	}
}
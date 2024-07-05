package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.LocatorType;
import pageUIs.SearchPageUI;

public class SearchPageObject  extends BasePage {
	private WebDriver driver;
	
	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToSearchTextBox(String value) {
		waitForElementVisible(driver, SearchPageUI.SEARCH_TEXT_BOX);
		sendKeyToElement(driver, SearchPageUI.SEARCH_TEXT_BOX, value);
	}

	public String getWarningTextBox() {
		waitForElementVisible(driver, SearchPageUI.WARNING_TEXT_BOX);
		return getElementText(driver, SearchPageUI.WARNING_TEXT_BOX, LocatorType.XPATH);
	}
	
	public String getNoResultTextBox() {
		waitForElementVisible(driver, SearchPageUI.NO_RESULT_TEXT_BOX);
		return getElementText(driver, SearchPageUI.NO_RESULT_TEXT_BOX, LocatorType.XPATH);
	}

	public void clickToSearchButton(WebDriver driver) {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON, LocatorType.XPATH);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON, LocatorType.XPATH);
	}

	public int getTotalItemProductResult() {
		waitForAllElementVisible(driver, SearchPageUI.ITEM_PRODUCT);
		return getElementSize(driver, SearchPageUI.ITEM_PRODUCT);
	}

	public String getTitleAtItem(int i) {
		waitForElementVisible(driver, SearchPageUI.TITLE_PRODUCT_TEXT_BOX, String.valueOf(i));
		return getElementTextByXpath(driver, SearchPageUI.TITLE_PRODUCT_TEXT_BOX, String.valueOf(i));
	}

	public void clickToAdvancedSearchCheckBox() {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_SEARCH_CHECKBOX, "Advanced search");
		checkToDefaultCheckboxRadioByXpath(driver, SearchPageUI.DYNAMIC_SEARCH_CHECKBOX,  "Advanced search");
	}

	public void chooseDynamicSearchDropdownByValue(String headerSelect, String value) {
		waitForElementClickable(driver, SearchPageUI.DYNAMIC_SEARCH_DROPDOWN, headerSelect);
		selectItemInDefaultDropdownByXpath(driver, value, SearchPageUI.DYNAMIC_SEARCH_DROPDOWN,  headerSelect);
	}

	public void unCheckToSubCategoriesCheckBox() {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_SEARCH_CHECKBOX, "Automatically search sub categories");
		unCheckToDefaultCheckboxRadioByXpath(driver, SearchPageUI.DYNAMIC_SEARCH_CHECKBOX, "Automatically search sub categories");
	}

	public void checkToSubCategoriesCheckBox() {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_SEARCH_CHECKBOX, "Automatically search sub categories");
		checkToDefaultCheckboxRadioByXpath(driver, SearchPageUI.DYNAMIC_SEARCH_CHECKBOX, "Automatically search sub categories");
	}


}

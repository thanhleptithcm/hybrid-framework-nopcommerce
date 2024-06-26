package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInfoUI;

public class CustomerInfoPageObject extends BasePage {
	private WebDriver driver;
	
	public CustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToFemaleRadioButton() {
		waitForElementVisible(driver, CustomerInfoUI.FEMALE_RADIO_BUTTON);
		checkToDefaultCheckboxRadio(driver, CustomerInfoUI.FEMALE_RADIO_BUTTON);
	}

	public void inputToFirstNameTextBox(String value) {
		waitForElementVisible(driver, CustomerInfoUI.FIRST_NAME_TEXT_BOX);
		sendKeyToElement(driver, CustomerInfoUI.FIRST_NAME_TEXT_BOX, value);
	}

	public void inputToLastNameTextBox(String value) {
		waitForElementVisible(driver, CustomerInfoUI.LAST_NAME_TEXT_BOX);
		sendKeyToElement(driver, CustomerInfoUI.LAST_NAME_TEXT_BOX, value);
	}

	public void chooseDayDropdown(String value) {
		waitForElementClickable(driver, CustomerInfoUI.DAY_DROPDOWN);
		selectItemInDefaultDropdown(driver, value, CustomerInfoUI.DAY_DROPDOWN);
	}

	public void chooseMonthDropdown(String value) {
		waitForElementClickable(driver, CustomerInfoUI.MONTH_DROPDOWN);
		selectItemInDefaultDropdown(driver, value, CustomerInfoUI.MONTH_DROPDOWN);
	}

	public void chooseYearDropdown(String value) {
		waitForElementClickable(driver, CustomerInfoUI.YEAR_DROPDOWN);
		selectItemInDefaultDropdown(driver, value, CustomerInfoUI.YEAR_DROPDOWN);
	}


	public void inputToEmailTextBox(String value) {
		waitForElementVisible(driver, CustomerInfoUI.EMAIL_TEXT_BOX);
		sendKeyToElement(driver, CustomerInfoUI.EMAIL_TEXT_BOX, value);
	}
	
	public void inputToCompanyNameTextBox(String value) {
		waitForElementVisible(driver, CustomerInfoUI.COMPANY_NAME_TEXT_BOX);
		sendKeyToElement(driver, CustomerInfoUI.COMPANY_NAME_TEXT_BOX, value);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, CustomerInfoUI.SAVE_BUTTON);
		clickToElement(driver, CustomerInfoUI.SAVE_BUTTON);
	}

	public boolean isFemaleRadioButtonSelected() {
		waitForElementVisible(driver, CustomerInfoUI.FEMALE_RADIO_BUTTON);
		return isElementSelected(driver, CustomerInfoUI.FEMALE_RADIO_BUTTON);
	}

	public String getValueAtFirstNameTextBox() {
		waitForElementVisible(driver, CustomerInfoUI.FIRST_NAME_TEXT_BOX);
		return getElementAttribute(driver,CustomerInfoUI.FIRST_NAME_TEXT_BOX, "value");
	}

	public String getValueAtLastNameTextBox() {
		waitForElementVisible(driver, CustomerInfoUI.LAST_NAME_TEXT_BOX);
		return getElementAttribute(driver,CustomerInfoUI.LAST_NAME_TEXT_BOX, "value");
	}

	public String getValueAtDayDropdown() {
		waitForElementVisible(driver, CustomerInfoUI.DAY_DROPDOWN);
		return getSelectedItemDefaultDropdown(driver, CustomerInfoUI.DAY_DROPDOWN);
	}

	public String getValueAtMonthDropdown() {
		waitForElementVisible(driver, CustomerInfoUI.MONTH_DROPDOWN);
		return getSelectedItemDefaultDropdown(driver, CustomerInfoUI.MONTH_DROPDOWN);
	}

	public String getValueAtYearDropdown() {
		waitForElementVisible(driver, CustomerInfoUI.YEAR_DROPDOWN);
		return getSelectedItemDefaultDropdown(driver, CustomerInfoUI.YEAR_DROPDOWN);
	}

	public String getValueAtEmailTextBox() {
		waitForElementVisible(driver, CustomerInfoUI.EMAIL_TEXT_BOX);
		return getElementAttribute(driver,CustomerInfoUI.EMAIL_TEXT_BOX, "value");
	}

	public String getValueAtCompanyNameTextBox() {
		waitForElementVisible(driver, CustomerInfoUI.COMPANY_NAME_TEXT_BOX);
		return getElementAttribute(driver,CustomerInfoUI.COMPANY_NAME_TEXT_BOX, "value");
	}
}
package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.AddressesPageObject;
import pageObjects.CustomerInfoPageObject;
import pageUIs.BasePageUI;
import pageUIs.MyAccountPageUI;

public class BasePage {

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicit.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}
	
	public  Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) { 
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToTabByTitle(WebDriver driver, String expectedTitle) { 
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParent(WebDriver driver, String parentID) { 
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	private String getDynamicLocatorXpath(String xpathLocator, String... dynamicValues) {
		xpathLocator = String.format(xpathLocator,(Object[]) dynamicValues);
		return xpathLocator;
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public WebElement getWebElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		getWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues)).click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String textValue) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		WebElement element = getWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String textItem, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdownByXpath(WebDriver driver, String textItem, String locator, String... dynamicValues) {
		Select select = new Select(getWebElementByXpath(driver, getDynamicLocatorXpath(locator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
		getWebElementByXpath(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItemText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String dynamicValues) {
		return getWebElementByXpath(driver, getDynamicLocatorXpath(locator, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	public String getElementTextByXpath(WebDriver driver, String locator) {
		return getWebElementByXpath(driver, locator).getText();
	}

	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	public String getHexaColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public List<WebElement> getListWebElementByXpath(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));

	}

	public int getElementSize(WebDriver driver, String xpathLocator){
		return getListWebElement(driver, xpathLocator).size();
	}

	public int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues){
		return getListWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues)).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadioByXpath(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues));
		if(!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToDefaultCheckboxRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToDefaultCheckboxRadioByXpath(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues));
		if(element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElement(driver, locator);
		overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		if(elements.size() == 0) {
			return true;
		} else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideImplicitTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	public void pressKeyToElement(WebDriver driver, Keys key, String locator) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}


	public void pressKeyToElement(WebDriver driver, Keys key, String xpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues)), key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		return status;
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator, String... dynamicValues) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElementByXpath(driver, getDynamicLocatorXpath(xpathLocator, dynamicValues)));
		return status;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocatorXpath(xpathLocator, dynamicValues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocatorXpath(xpathLocator, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForElementInvisibleByXpath(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	public void waitForElementUndisplayed(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideImplicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
		overrideImplicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}


	public void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocatorXpath(xpathLocator, dynamicValues))));
	}
	
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	public void inputToTextboxByName(WebDriver driver, String textboxName, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
		sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, value, textboxName);
	}
	
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	public void clickToButtonByName(WebDriver driver, String buttonName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME, buttonName);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME, buttonName);
	}
	
	public void selectToDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaultDropdownByXpath(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
	}
	
	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		checkToDefaultCheckboxRadioByXpath(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}
	
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxRadioByXpath(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for(String file : fileNames) {
			fullFileName = fullFileName + GlobalConstants.UPLOAD_FILE_FOLDER + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElementByXpath(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public CustomerInfoPageObject clickToCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, String.format(MyAccountPageUI.DYNAMIC_MENU_BY_NAME, "Customer info"));
		clickToElement(driver, String.format(MyAccountPageUI.DYNAMIC_MENU_BY_NAME, "Customer info"));
		return PageGeneraterManager.getCustomerInfoPageObject(driver);
	}
	

	public AddressesPageObject clickToAddressesPageObject(WebDriver driver) {
		waitForElementClickable(driver, String.format(MyAccountPageUI.DYNAMIC_MENU_BY_NAME, "Addresses"));
		clickToElement(driver, String.format(MyAccountPageUI.DYNAMIC_MENU_BY_NAME, "Addresses"));
		return PageGeneraterManager.getAddressesPageObject(driver);
	}
}

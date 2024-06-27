package pageUIs;

public class BasePageUI {

	public static final String DYNAMIC_PAGE_LINK_AT_MY_ACCOUNT_AREA = "//a[text()='%s']";
	
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
	public static final String DYNAMIC_BUTTON_BY_NAME = "//input[@name='%s']";
	
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL =  "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL =  "//label[contains(text(), '%s']/following-sibling::input";
	
	public static final String UPLOAD_FILE = "//input[@type='file']";
}
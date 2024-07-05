package pageUIs;

public class BasePageUI {

	public static final String LOGOUT_LINK = "//a[@class='ico-logout']";
	
	public static final String DYNAMIC_PAGE_LINK_AT_MY_ACCOUNT_AREA = "//a[text()='%s']";
	
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
	public static final String DYNAMIC_BUTTON_BY_NAME = "//input[@name='%s']";
	
	public static final String DYNAMIC_DROPDOWN_BY_ID = "//select[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL =  "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL =  "//label[contains(text(), '%s']/following-sibling::input";
	
	public static final String UPLOAD_FILE = "//input[@type='file']";
	
	
	public static final String DYNAMIC_VALUE_ADDRESSES_BY_CLASS = "//li[@class='%s']";
	

	public static final String BAR_NOTIFICATION_TEXT_BOX = "//div[@id='bar-notification']//p[@class='content']";
	public static final String CLOSE_BAR_NOTIFICATION_SPAN = "//div[@class='bar-notification success']//span";
	

	public static final String TITLE_HEADER_H1 = "//div[@class='page-title']/h1";
	
}
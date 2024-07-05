package pageUIs;

public class SearchPageUI {
	
	public static final String SEARCH_TEXT_BOX = "//input[@class='search-text']";
	public static final String SEARCH_BUTTON = "//button[@class='button-1 search-button']";
	public static final String WARNING_TEXT_BOX = "//div[@class='warning']";
	public static final String NO_RESULT_TEXT_BOX = "//div[@class='no-result']";
	
	
	public static final String ITEM_PRODUCT = "//div[@class='item-box']";
	public static final String TITLE_PRODUCT_TEXT_BOX = "//div[@class='item-box'][%s]//h2[@class='product-title']/a";
	
	public static final String DYNAMIC_SEARCH_CHECKBOX = "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_SEARCH_DROPDOWN = "//label[text()='%s']/following-sibling::select";
	
}

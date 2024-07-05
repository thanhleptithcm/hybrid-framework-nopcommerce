package pageUIs;

public class ProductPageUI {
	public static final String ADD_REVIEW_LINK = "//div[@class='product-review-links add-review']";
	public static final String REVIEW_TEXT_AREA = "//textarea[@id='AddProductReview_ReviewText']";
	public static final String RATING_RADIO = "//input[@id='addproductrating_%s']";
	
	public static final String DYNAMIC_REVIEW_TITLE_TEXT_BOX = "//div[@class='product-review-item'][1]//div[@class='review-title']/strong[text()='%s']";
	public static final String REVIEW_RATING_TEXT_BOX = "//div[@class='product-review-item'][1]//div[@class='rating']/div";
	public static final String DYNAMIC_REVIEW_DESC_TEXT_BOX = "//div[@class='product-review-item'][1]//div[@class='review-content']/div[@class='review-text']/div[text()='%s']";
	
	public static final String EXISTING_REVIEW_TEXT_BOX = "//div[@class='title']/strong[text()='Existing reviews']";
}
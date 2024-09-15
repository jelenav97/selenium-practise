package practise.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(id = "toast-container")
	WebElement popUpMessage;

	@FindBy(className = "ng-animating")
	WebElement spinner;

	By addToCartLocator = By.cssSelector(".btn.w-10.rounded");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(products.get(0));
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProductsList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
	}

	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCartLocator).click();
		waitForElementToDisappear(spinner);
	}

	public String getPopUpMessage() {
		waitForElementToAppear(popUpMessage);
		return popUpMessage.getText();
	}

	public String getProductCataloguePageUrl() {
		waitForElementToAppear(products.get(0));
		return driver.getCurrentUrl();
	}

}

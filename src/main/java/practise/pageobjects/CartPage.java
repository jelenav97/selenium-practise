package practise.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> items;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	public Boolean verifyProductDisplay(String productName) {
		return items.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
	}

	public int numberOfProductsInCart() {
		return items.size();
	}

	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
}

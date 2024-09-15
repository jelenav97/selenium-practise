package practise.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import practise.pageobjects.CartPage;
import practise.pageobjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='/dashboard/cart']")
	WebElement cartNavButton;

	@FindBy(css = "[routerlink='/dashboard/myorders']")
	WebElement ordersNavButton;

	public void waitForElementToAppear(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	public void waitForElementToDisappear(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(elem));
	}

	public CartPage goToCart() {
		cartNavButton.click();
		return new CartPage(driver);
	}

	public OrdersPage goToOrdersPage() {
		ordersNavButton.click();
		return new OrdersPage(driver);
	}
}

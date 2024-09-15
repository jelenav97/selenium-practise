package practise.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".form-group")
	WebElement countryInput;

	@FindBy(css = ".ta-item:nth-of-type(1)")
	WebElement selectedCountry;

	@FindBy(css = ".action__submit")
	WebElement checkoutButton;

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(countryInput, countryName).build().perform();
		selectedCountry.click();
	}

	public ConfirmationPage submitOrder() {
		Actions a = new Actions(driver);
		a.click(checkoutButton).build().perform();
		return new ConfirmationPage(driver);
	}
}

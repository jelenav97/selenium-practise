package practise.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordElement;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = ".forgot-password-link")
	WebElement forgotPassword;

	@FindBy(id = "toast-container")
	WebElement popUpMessage;

	@FindBy(xpath = "//div[@class='form-group']/div[@class='invalid-feedback']/div")
	WebElement emailErrorMessage;

	@FindBy(xpath = "//div[@class='form-group mb-4']/div[@class='invalid-feedback']/div")
	WebElement passwordErrorMessage;

	@FindBy(css = "[href='/client/auth/password-new']")
	WebElement forgotPasswordButton;

	@FindBy(css = ".login-wrapper-footer-text")
	WebElement registrationButton;

	public ProductCataloguePage loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		return new ProductCataloguePage(driver);
	}

	public void goToLandingPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ForgotPasswordPage goToForgotPasswordPage() {
		forgotPassword.click();
		return new ForgotPasswordPage(driver);
	}

	public String getPopUpMessage() {
		waitForElementToAppear(popUpMessage);
		return popUpMessage.getText();
	}

	public String getLandingPageUrl() {
		waitForElementToAppear(submit);
		return driver.getCurrentUrl();
	}

	public String getInvalidEmailMessage() {
		return emailErrorMessage.getText();
	}

	public String getInvalidPasswordMessage() {
		return passwordErrorMessage.getText();
	}

	public RegistrationPage goToRegistrationPage() {
		registrationButton.click();
		return new RegistrationPage(driver);
	}
}

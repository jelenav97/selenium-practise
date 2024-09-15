package practise.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practise.AbstractComponents.AbstractComponents;

public class ForgotPasswordPage extends AbstractComponents {

	WebDriver driver;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='email']")
	WebElement emailInput;

	@FindBy(id = "userPassword")
	WebElement passwordInput;

	@FindBy(id = "confirmPassword")
	WebElement confirmPasswordInput;

	@FindBy(css = ".btn.btn-custom.btn-block")
	WebElement submitButton;

	@FindBy(css = "[href='/client/auth/login']")
	WebElement loginPageButton;

	@FindBy(css = "[href='/client/auth/register']")
	WebElement registrationPageButton;

	@FindBy(css = ".form-group.mt-2.mb-2 div.invalid-feedback")
	WebElement emailErrorMessage;

	@FindBy(xpath = "//input[@id='userPassword']/following-sibling::div[@class='invalid-feedback']")
	WebElement passwordErrorMessage;

	@FindBy(xpath = "//input[@id='confirmPassword']/following-sibling::div[@class='invalid-feedback']")
	WebElement confirmPasswordErrorMessage;

	@FindBy(id = "toast-container")
	WebElement popUpMessage;

	public void sendForgotPasswordInputs(String email, String password, String confirmPassword) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		confirmPasswordInput.sendKeys(confirmPassword);
		submitButton.click();
	}

	public String getInvalidEmailMessage() {
		return emailErrorMessage.getText();
	}

	public String getInvalidPasswordMessage() {
		return passwordErrorMessage.getText();
	}

	public String getInvalidConfirmPasswordMessage() {
		return confirmPasswordErrorMessage.getText();
	}

	public String getPopUpMessage() {
		waitForElementToAppear(popUpMessage);
		return popUpMessage.getText();
	}

	public void goToLandingPage() {
		loginPageButton.click();
	}

	public RegistrationPage goToRegistrationPage() {
		registrationPageButton.click();
		return new RegistrationPage(driver);
	}

	public String getForgotPasswordPageUrl() {
		waitForElementToAppear(confirmPasswordInput);
		return driver.getCurrentUrl();
	}

}

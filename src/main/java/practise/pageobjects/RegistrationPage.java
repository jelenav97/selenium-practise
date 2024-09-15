package practise.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import practise.AbstractComponents.AbstractComponents;

public class RegistrationPage extends AbstractComponents {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "firstName")
	WebElement firstNameInput;

	@FindBy(id = "lastName")
	WebElement lastNameInput;

	@FindBy(id = "userEmail")
	WebElement emailInput;

	@FindBy(id = "userMobile")
	WebElement mobileInput;

	@FindBy(xpath = "//select[@formcontrolname='occupation']")
	WebElement occupationDropDown;

	@FindBy(id = "userPassword")
	WebElement passwordInput;

	@FindBy(id = "confirmPassword")
	WebElement confirmPasswordInput;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement checkboxInput;

	@FindBy(xpath = "//input[@value='Register']")
	WebElement registerButton;

	@FindBy(id = "toast-container")
	WebElement popUpMessage;

	@FindBy(xpath = "//div[@class='col-md-1']/following-sibling::div[last()]")
	WebElement checkboxErrorMessage;

	@FindBy(css = ".btn.btn-primary")
	WebElement loginButton;

	public String getRegistrationPageUrl() {
		waitForElementToAppear(registerButton);
		return driver.getCurrentUrl();
	}

	public void submitRegistration(String firstName, String lastName, String email, String mobileNumber,
			String occupation, String gender, String password, String confirmPassword, String checkbox) {
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		emailInput.sendKeys(email);
		mobileInput.sendKeys(mobileNumber);

		if (!occupation.isBlank()) {
			Select dropdown = new Select(occupationDropDown);
			dropdown.selectByVisibleText(occupation);
		}

		if (!gender.isBlank()) {
			driver.findElement(By.xpath("//input[@value='" + gender + "']")).click();
		}

		passwordInput.sendKeys(password);
		confirmPasswordInput.sendKeys(confirmPassword);

		if (checkbox.equalsIgnoreCase("selected")) {
			checkboxInput.click();
		}

		registerButton.click();
	}

	public String getPopUpMessage() throws InterruptedException {
		Thread.sleep(1000);
		waitForElementToAppear(popUpMessage);
		return popUpMessage.getText();
	}

	public String getErrorMessage(String type) {
		return driver.findElement(By.xpath("//input[@id='" + type + "']/following-sibling::div/div")).getText();
	}

	public String getCheckboxErrorMessage() {
		return checkboxErrorMessage.getText();
	}

	public void goToLoginAfterRegistration() {
		loginButton.click();
	}

}

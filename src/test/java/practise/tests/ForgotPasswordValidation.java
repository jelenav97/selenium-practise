package practise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import practise.TestComponents.BaseTest;
import practise.pageobjects.ForgotPasswordPage;
import practise.pageobjects.ProductCataloguePage;
import practise.pageobjects.RegistrationPage;

public class ForgotPasswordValidation extends BaseTest {
	
	//Password reset without providing Email Input
	@Test
	public void invalidEntryWithoutEmailInputTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.sendForgotPasswordInputs("", "test", "test");
		// validate that correct error message is displayed
		Assert.assertEquals(forgotPasswordPage.getInvalidEmailMessage(), "*Email is required");
	}

	//Password reset without providing Password Input
	@Test
	public void invalidEntryWithoutPasswordInputTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.sendForgotPasswordInputs("jelena@test.com", "", "test");
		// validate that correct error message is displayed
		Assert.assertEquals(forgotPasswordPage.getInvalidPasswordMessage(), "*Password is required");
		Assert.assertEquals(forgotPasswordPage.getInvalidConfirmPasswordMessage(),
				"Password and Confirm Password must match with each other.");
	}

	//Password reset without providing Confirm Input
	@Test
	public void invalidEntryWithoutConfirmPasswordInputTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.sendForgotPasswordInputs("jelena@test.com", "test", "");
		// validate that correct error message is displayed
		Assert.assertEquals(forgotPasswordPage.getInvalidConfirmPasswordMessage(), "*Confirm Password is required");
	}

	//Password reset when Password and Confirm Password fields do not match
	@Test
	public void mismatchPasswordAndConfirmPasswordFieldsTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.sendForgotPasswordInputs("jelena@test.com", "test", "test1");
		// validate that correct error message is displayed
		Assert.assertEquals(forgotPasswordPage.getInvalidConfirmPasswordMessage(),
				"Password and Confirm Password must match with each other.");
	}

	//Password reset when entered email is invalid
	@Test
	public void invalidEmailEntryTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.sendForgotPasswordInputs("jelenatest.com", "test", "test");
		// validate that correct error message is displayed
		Assert.assertEquals(forgotPasswordPage.getInvalidEmailMessage(), "*Enter Valid Email");
	}

	//Password reset for User that is not registered
	@Test
	public void nonExistingUserEntryTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.sendForgotPasswordInputs("jelena5@test.com", "test", "test");
		Assert.assertEquals(forgotPasswordPage.getPopUpMessage(), "User Not found.");
	}

	//Password reset when all inputs are correct
	@Test
	public void forrgotPasswordValidationTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.sendForgotPasswordInputs("jelena@test.com", "test", "test");
		Assert.assertEquals(landingPage.getLandingPageUrl(), "https://rahulshettyacademy.com/client/auth/login");
		Assert.assertEquals(landingPage.getPopUpMessage(), "Password Changed Successfully");
	}

	//Login after password reset
	@Test(dependsOnMethods = { "forrgotPasswordValidationTest" })
	public void loginAfterPasswordResetTest() throws InterruptedException {
		ProductCataloguePage productPage = landingPage.loginApplication("jelena@test.com", "test");
		Assert.assertEquals(productPage.getProductCataloguePageUrl(),
				"https://rahulshettyacademy.com/client/dashboard/dash");
		Assert.assertEquals(productPage.getPopUpMessage(), "Login Successfully");
	}

	//Validation of login button
	@Test
	public void loginButtonTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		forgotPasswordPage.goToLandingPage();
		Assert.assertEquals(landingPage.getLandingPageUrl(), "https://rahulshettyacademy.com/client/auth/login");
	}

	//Validation of login button
	@Test
	public void registerButtonValidation() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		RegistrationPage registrationPage = forgotPasswordPage.goToRegistrationPage();
		Assert.assertEquals(registrationPage.getRegistrationPageUrl(),
				"https://rahulshettyacademy.com/client/auth/register");
	}
}

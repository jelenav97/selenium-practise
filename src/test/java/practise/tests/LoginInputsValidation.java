package practise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import practise.TestComponents.BaseTest;
import practise.pageobjects.ForgotPasswordPage;
import practise.pageobjects.RegistrationPage;

public class LoginInputsValidation extends BaseTest {
	
	//Login without providing Email Input
	@Test
	public void invalidEntryWithoutEmailInputTest() {
		landingPage.loginApplication("", "Test123/");
		// validate that correct error message is displayed
		Assert.assertEquals(landingPage.getInvalidEmailMessage(), "*Email is required");
	}

	//Login without providing Password Input
	@Test
	public void invalidEntryWithoutPasswordInputTest() {
		landingPage.loginApplication("jelena@test.com", "");
		// validate that correct error message is displayed
		Assert.assertEquals(landingPage.getInvalidPasswordMessage(), "*Password is required");
	}

	//Login with invalid email entry
	@Test
	public void invalidEmailEntryTest() {
		landingPage.loginApplication("jelenatest.com", "Test123/");
		// validate that correct error message is displayed
		Assert.assertEquals(landingPage.getInvalidEmailMessage(), "*Enter Valid Email");
	}

	//Login with user that did not register
	@Test
	public void invalidEntriesTest() {
		landingPage.loginApplication("jelena5@test.com", "test");
		Assert.assertEquals(landingPage.getPopUpMessage(), "Incorrect email or password.");
	}

	//forgot password button validation
	@Test
	public void forgotPasswordButtonTest() {
		ForgotPasswordPage forgotPasswordPage = landingPage.goToForgotPasswordPage();
		Assert.assertEquals(forgotPasswordPage.getForgotPasswordPageUrl(),
				"https://rahulshettyacademy.com/client/auth/password-new");
	}

	//registration password button validation
	@Test
	public void registerButtonTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		Assert.assertEquals(registrationPage.getRegistrationPageUrl(),
				"https://rahulshettyacademy.com/client/auth/register" );
	}

}

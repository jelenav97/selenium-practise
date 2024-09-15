package practise.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practise.TestComponents.BaseTest;
import practise.pageobjects.RegistrationPage;

public class RegistrationInputsValidation extends BaseTest {

	//Registration without First Name input
	@Test
	public void invalidEntryWithoutFirstNameInputTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("", "lastname", "testing123@testing.com", "1234567890", "", "", "Test123/",
				"Test123/", "selected");
		Assert.assertEquals(registrationPage.getErrorMessage("firstName"), "*First Name is required");
	}

	//Registration without Last Name input
	@Test
	public void invalidEntryWithoutLastNameInputTest() throws InterruptedException {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstname", "", "testing123@testing.com", "1234567890", "", "", "Test123/",
				"Test123/", "selected");
		Assert.assertEquals(registrationPage.getPopUpMessage(), "Last Name is required!");
	}

	//Registration without Phone Number input
	@Test
	public void invalidEntryWithoutMobileNumberInputTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstname", "lastname", "testing123@testing.com", "", "", "", "Test123/",
				"Test123/", "selected");
		Assert.assertEquals(registrationPage.getErrorMessage("userMobile"), "*Phone Number is required");
	}

	//Registration without Password input
	@Test
	public void invalidEntryWithoutPasswordInputTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstname", "lastname", "testing123@testing.com", "1234567890", "", "", "",
				"Test123/", "selected");
		Assert.assertEquals(registrationPage.getErrorMessage("userPassword"), "*Password is required");
		Assert.assertEquals(registrationPage.getErrorMessage("confirmPassword"),
				"Password and Confirm Password must match with each other.");
	}

	//Registration without Mobile Number input
	@Test
	public void invalidEntryWithoutConfirmPasswordInpuTestt() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstname", "lastname", "testing123@testing.com", "1234567890", "", "",
				"Test123/", "", "selected");
		Assert.assertEquals(registrationPage.getErrorMessage("confirmPassword"), "Confirm Password is required");
	}

	//Registration without clicking checkbox 
	@Test
	public void invalidEntryWithoutCheckboxInputTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstname", "lastname", "testing123@testing.com", "1234567890", "", "",
				"Test123/", "Test123/", "");
		Assert.assertEquals(registrationPage.getCheckboxErrorMessage(), "*Please check above checkbox");
	}

	//Registration when first name is less than 3 characters 
	@Test
	public void invalidEntryWithShortFirstNameInputTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("ab", "lastname", "testing123@testing.com", "1234567890", "", "",
				"Test123/", "Test123/", "selected");
		Assert.assertEquals(registrationPage.getErrorMessage("firstName"),
				"*First Name must be 3 or more character long");
	}

	//Registration when first name is more than 12 characters 
	@Test
	public void invalidEntryWithLongFirstNameInputTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("awnrktnjlonis", "lastname", "testing123@testing.com", "1234567890", "", "",
				"Test123/", "Test123/", "selected");
		Assert.assertEquals(registrationPage.getErrorMessage("firstName"),
				"*First Name must be 12 or less character long");
	}

	//Registration when last name is less than 3 characters 
	@Test
	public void invalidEntryWithShortLasttNameInputTest() throws InterruptedException {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstName", "la", "testing123@testing.com", "1234567890", "", "",
				"Test123/", "Test123/", "selected");
		Assert.assertEquals(registrationPage.getPopUpMessage(), "last Name must be 3 to 20 characters long!");
	}

	//Registration when first name is more than 20 characters 
	@Test
	public void invalidEntryWithLongLastNameInputTest() throws InterruptedException {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstName", "awnrktnjlonisnsknzgna", "testing123@testing.com",
				"1234567890", "", "", "Test123/", "Test123/", "selected");
		Assert.assertEquals(registrationPage.getPopUpMessage(), "last Name must be 3 to 20 characters long!");
	}

	//Registration when Phone Numer input is invalid
	@Test(dataProvider = "invalidPhoneNumberInputs")
	public void invalidMobileNumberInputTest(String phoneNumber) {

		RegistrationPage registrationPage = landingPage.goToRegistrationPage();

		registrationPage.submitRegistration("firstname", "lastname", "testing123@testing.com", phoneNumber, "", "",
				"Test123/", "Test123/", "selected");

		if (phoneNumber.length() != 10) {
			Assert.assertEquals(registrationPage.getErrorMessage("userMobile"), "*Phone Number must be 10 digit");
		} else {
			Assert.assertEquals(registrationPage.getErrorMessage("userMobile"), "*only numbers is allowed");
		}
	}

	//Registration when password and confirm password inputs do not match
	@Test
	public void passwordAndConfirmPasswordMismatchTest() {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstname", "lastname", "testing123@testing.com", "1234567890", "", "",
				"Test123!", "Test123/", "selected");
		// validate that correct error message is displayed
		Assert.assertEquals(registrationPage.getErrorMessage("confirmPassword"),
				"Password and Confirm Password must match with each other.");
	}

	//Registration when Password and Confirm Password inputs are invalid
	@Test(dataProvider = "invalidPasswordInputs")
	public void invalidPasswordInputsTest(String email, String password) throws InterruptedException {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();

		registrationPage.submitRegistration("firstname", "lastname", email, "1234567890", "", "", password, password,
				"selected");

		if (password.length() != 8) {
			Assert.assertEquals(registrationPage.getPopUpMessage(), "Password must be 8 Character Long!");
		} else {
			Assert.assertEquals(registrationPage.getPopUpMessage(),
					"Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small");
		}
	}

	//Registration whit user email that is already registered
	@Test
	public void alreadyCreatedUserTest() throws InterruptedException {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();
		registrationPage.submitRegistration("firstname", "lastname", "jelena@test.com", "1234567890", "", "",
				"Test123/", "Test123/", "selected");
		Assert.assertEquals(registrationPage.getPopUpMessage(), "User already exisits with this Email Id!");
	}

	@DataProvider(name = "invalidPhoneNumberInputs")
	public Object[][] getInvalidMobilePhoneInputs() {
		return new Object[][] { { "1" }, { "12345" }, { "12345678901" }, { "aimnkzfbga" }, { "123456a789" },
				{ "!@#$%^&*()" }, { "123.567890" }, { "12m14/46m6" } };
	}

	@DataProvider(name = "invalidPasswordInputs")
	public Object[][] getInvalidpasswordInputs() {
		return new Object[][] { { "testing0801@test.com", "a" }, { "testing0802@test.com", "aA!8" },
				{ "testing0803@test.com", "!b9xM1/" }, { "testing0804@test.com", "2?ba12m9" },
				{ "testing0805@test.com", "A/aaabB#" }, { "testing0805@test.com", "2AB/F90%" },
				{ "testing0808@test.com", "TeSt12dm" } };
	}

}

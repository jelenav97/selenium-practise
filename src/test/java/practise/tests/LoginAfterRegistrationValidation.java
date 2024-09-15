package practise.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practise.TestComponents.BaseTest;
import practise.pageobjects.ProductCataloguePage;
import practise.pageobjects.RegistrationPage;

public class LoginAfterRegistrationValidation extends BaseTest {

	//Login right after registration
	@Test(dataProvider = "validInputs")
	public void loginAfterRegistrationTest(HashMap<String, String> input) throws InterruptedException {
		RegistrationPage registrationPage = landingPage.goToRegistrationPage();

		registrationPage.submitRegistration(input.get("firstName"), input.get("lastName"), input.get("email"),
				input.get("phoneNumber"), input.get("occupation"), input.get("gender"), input.get("password"),
				input.get("confirmPassword"), "selected");

		Assert.assertEquals(registrationPage.getPopUpMessage(), "Registered Successfully");

		registrationPage.goToLoginAfterRegistration();
		ProductCataloguePage productPage = landingPage.loginApplication(input.get("email"), input.get("password"));

		Assert.assertEquals(productPage.getProductCataloguePageUrl(),
				"https://rahulshettyacademy.com/client/dashboard/dash");
		Assert.assertEquals(productPage.getPopUpMessage(), "Login Successfully");
	}

	//Login of already registered users
	@Test(dataProvider = "validInputs", dependsOnMethods = "loginAfterRegistrationTest")
	public void loginValidationTest(HashMap<String, String> input) {
		ProductCataloguePage productPage = landingPage.loginApplication(input.get("email"), input.get("password"));
		Assert.assertEquals(productPage.getProductCataloguePageUrl(),
				"https://rahulshettyacademy.com/client/dashboard/dash");
		Assert.assertEquals(productPage.getPopUpMessage(), "Login Successfully");
	}

	@DataProvider(name = "validInputs")
	public Object[][] getValidData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//practise//data//RegistrationInputs.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) }, { data.get(3) }, { data.get(4) },
				{ data.get(5) }, { data.get(6) } };
	}
}

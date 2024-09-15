package practise.tests;

import org.testng.annotations.DataProvider;
import org.testng.AssertJUnit;
import practise.TestComponents.BaseTest;
import practise.pageobjects.CartPage;
import practise.pageobjects.CheckoutPage;
import practise.pageobjects.ConfirmationPage;
import practise.pageobjects.OrdersPage;
import practise.pageobjects.ProductCataloguePage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {

	//Validates that order can be submitted 
	@Test(dataProvider = "getData")
	public void submitOrderTest(HashMap<String, String> input) throws IOException {
		String country = "cro";

		// landing (login) page
		ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"),
				input.get("password"));
		productCataloguePage.addProductToCart(input.get("product"));

		// cart page
		CartPage cartPage = productCataloguePage.goToCart();
		Assert.assertEquals(cartPage.numberOfProductsInCart(), 1);
		Assert.assertTrue(cartPage.verifyProductDisplay(input.get("product")));

		// checkout
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(country);

		// confirmation page
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		AssertJUnit.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
	}

	//Validates that only one order is submitted in orders page
	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrderTest" })
	public void validateCorrectNumberOfOrdersTest(HashMap<String, String> input) {
		ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"),
				input.get("password"));
		OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
		Assert.assertEquals(ordersPage.getNumberOfOrders(), 1);
	}

	//Validates that order is present in order history
	@Test(dataProvider = "getData", dependsOnMethods = { "validateCorrectNumberOfOrdersTest" })
	public void orderHistoryTest(HashMap<String, String> input) {
		// "ZARA COAT 3"
		ProductCataloguePage productCataloguePage = landingPage.loginApplication(input.get("email"),
				input.get("password"));
		OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("product")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//practise//data//RegistrationInputs.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

}

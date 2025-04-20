package com.automation.selenium_automation.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.selenium_automation.helpers.Base;
import com.automation.selenium_automation.pages.CartPage;
import com.automation.selenium_automation.pages.CheckoutPage;
import com.automation.selenium_automation.pages.ConfirmationPage;
import com.automation.selenium_automation.pages.HomePage;
import com.automation.selenium_automation.utils.Utils;

public class E2eTestTestNG extends Base {

	@Test(dataProvider = "getData")
	public void e2eTest(HashMap<String, Object> input) throws InterruptedException {
//		driver.manage().window().maximize();

		Object[] loginResult = loginPage.validLogin((String) input.get("email"), (String) input.get("password"));
		Assert.assertEquals(loginResult[0], "Login Successfully");
		HomePage homepage = (HomePage) loginResult[1];

		System.out.println("Window size: " + driver.manage().window().getSize());
		
		List<String> selectedItems = ((List<String>) input.get("products")).stream().sorted()
				.collect(Collectors.toList());
		homepage.addItemsToCart(selectedItems);
		CartPage cartpage = homepage.clickToCart();

		List<String> productTitles = cartpage.getProductTitles();
		Assert.assertTrue(selectedItems.equals(productTitles));
		CheckoutPage checkoutpage = cartpage.clickCheckOut();

		String initLetters = "Can";
		String targetCountry = "Canada";
		String selectedCountry = checkoutpage.selectCountry(initLetters, targetCountry);
		Assert.assertTrue(selectedCountry.equalsIgnoreCase(targetCountry));
		ConfirmationPage confirmationpage = checkoutpage.clickOrderBtn();

		String confirmationText = confirmationpage.getConfirmationText();
		Assert.assertTrue(confirmationText.equalsIgnoreCase("Thankyou for the order."));
		List<String> orderedProductTitles = confirmationpage.getProductTitles();
		Assert.assertTrue(orderedProductTitles.equals(selectedItems));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, Object>> data = Utils.getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/com/automation/selenium_automation/data/info.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}

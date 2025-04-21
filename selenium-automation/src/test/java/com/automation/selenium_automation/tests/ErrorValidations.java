package com.automation.selenium_automation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.selenium_automation.pages.HomePage;
import com.automation.selenium_automation.pages.OrdersPage;
import com.automation.selenium_automation.utils.Base;
import com.automation.selenium_automation.utils.Utils;

public class ErrorValidations extends Base {

	// error when logged in successfully
	@Test(groups= {"ErrorHandling"})
	public void loginErrorMessageValidation() {
		String errorMessage = tlLoginPage.get().invalidLogin("qwe123@daum.com", "Qwe123!!");
		Assert.assertEquals(errorMessage, "Incorrect banana or password.");
	}
	
	@Test(groups= {"ErrorHandling"})
	public void inValidEmailFormValidation() {
		String errorMessage = tlLoginPage.get().invalidEmail("qwe123", "Qwe123!!");
		Assert.assertTrue(errorMessage.contains("Enter Valid Email"));
	}

	// error when products are not matching products inside orders page
	@Test(groups = { "ErrorHandling" })
	public void orderErrorValidation() throws IOException {
		List<HashMap<String, Object>> data = Utils.getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/com/automation/selenium_automation/data/info.json");

		String email = (String) data.get(0).get("email");
		String password = (String) data.get(0).get("password");
		@SuppressWarnings("unchecked")
		List<String> products = ((List<String>) data.get(0).get("products")).stream().sorted()
				.collect(Collectors.toList());

		Object[] loginResult = tlLoginPage.get().validLogin(email, password);
		Assert.assertEquals(loginResult[0], "Login Successfully");
		HomePage homepage = (HomePage) loginResult[1];
		OrdersPage orderspage = homepage.clickToOrders();

		//sending products size to check how many items we purchased
		List<String> productNames = orderspage.getOrdersProduct(products.size());
		Assert.assertTrue(productNames.equals(products));

	}
}

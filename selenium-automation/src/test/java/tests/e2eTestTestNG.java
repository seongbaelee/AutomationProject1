package tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.selenium_automation.base.Base;
import com.automation.selenium_automation.pages.CartPage;
import com.automation.selenium_automation.pages.CheckoutPage;
import com.automation.selenium_automation.pages.ConfirmationPage;
import com.automation.selenium_automation.pages.HomePage;

public class e2eTestTestNG extends Base {

	@Test
	public void test () {
//		driver.manage().window().maximize();

		Object[] loginResult = loginPage.validLogin("qwe123@daum.com", "Qwe123!@");
		Assert.assertEquals(loginResult[0], "Login Successfully");
		HomePage homepage = (HomePage) loginResult[1];
		
		List<String> selectedItems = Arrays.asList("ZARA COAT 3", "IPHONE 13 PRO")
				.stream()
				.sorted()
				.collect(Collectors.toList());
		homepage.addItemsToCart(selectedItems);
		CartPage cartpage = homepage.clickToCart();
		
		List<String> productTitles =cartpage.getProductTitles();
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
}

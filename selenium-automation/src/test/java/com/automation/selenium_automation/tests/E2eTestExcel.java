package com.automation.selenium_automation.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.selenium_automation.pages.CartPage;
import com.automation.selenium_automation.pages.CheckoutPage;
import com.automation.selenium_automation.pages.ConfirmationPage;
import com.automation.selenium_automation.pages.HomePage;
import com.automation.selenium_automation.utils.Base;
import com.automation.selenium_automation.utils.Utils;

public class E2eTestExcel extends Base {
	@Test(dataProvider = "excelData")
	public void e2eTest(String email, String password, List<String> products) throws InterruptedException {
		Object[] loginResult = tlLoginPage.get().validLogin(email, (String) password);
		Assert.assertEquals(loginResult[0], "Login Successfully");
		HomePage homepage = (HomePage) loginResult[1];
		List<String> selectedItems = products.stream().sorted()
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
	
	@DataProvider(name = "excelData")
	public Object[][] getData() throws IOException {
		Object[][] data = Utils.getDataByExcel(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\automation\\selenium_automation\\data\\projectData.xlsx");

		return data;
	}
}

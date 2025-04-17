package tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.selenium_automation.base.Base;
import com.automation.selenium_automation.pages.CartPage;
import com.automation.selenium_automation.pages.CheckoutPage;
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
//		
//		String targetCountry = "Canada";
//		WebElement countryBtnEle = driver.findElement(By.cssSelector(".user__address input"));
//		countryBtnEle.sendKeys("can");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
//		driver.findElement(By.xpath("//span[contains(text() , '" + targetCountry + "')]")).click();
//		String selectedCountry = countryBtnEle.getDomProperty("value");
//		Assert.assertTrue(selectedCountry.equalsIgnoreCase(targetCountry));
//		driver.findElement(By.cssSelector(".action__submit")).click();
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
//		String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".line-item .m-3 .title")));
//		List<WebElement> orderedProductNamesEle = driver.findElements(By.cssSelector(".line-item .m-3 .title"));
//		List<String> orderedProductNames = orderedProductNamesEle.stream()
//				.map(titleEle -> titleEle.getText()).sorted().collect(Collectors.toList());
//		Assert.assertTrue(orderedProductNames.equals(sortedItems));
	}
}

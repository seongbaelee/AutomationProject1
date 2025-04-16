package tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.selenium_automation.base.Base;
import com.automation.selenium_automation.pages.LoginPage;

public class e2eTestTestNG extends Base {

	@Test
	public void test () {
//		driver.manage().window().maximize();

		Object[] loginResult = loginPage.login("qwe123@daum.com", "Qwe123!@");
		
		Assert.assertEquals(loginResult[0], "Login Successfully");
		
//		String[] wishList = {"ZARA COAT 3", "IPHONE 13 PRO"};
//		List<String> selectedItems = Arrays.asList(wishList);
//		List<String> sortedItems = selectedItems.stream().sorted().collect(Collectors.toList());
//				
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".container div.row div.card-body")));
//		List<WebElement> itemList = driver.findElements(By.cssSelector(".container div.row div.card-body"));
//		itemList.stream().filter(item -> {
//			String itemName = item.findElement(By.tagName("h5")).getText();
//			return selectedItems.contains(itemName);
//		}).forEach(item -> {
//			WebElement addCartBtn = item.findElement(By.cssSelector("button:last-of-type"));
//			wait.until(x -> {
//			    List<WebElement> overlays = x.findElements(By.cssSelector(".ngx-spinner-overlay"));
//			    return overlays.isEmpty() || overlays.stream().allMatch(o -> {
//			        String style = o.getDomProperty("style");
//			        return style.contains("opacity: 0") || style.contains("display: none") || style.contains("visibility: hidden");
//			    });
//			});
//			addCartBtn.click();
//		});
//		
//		WebElement cartBtn = driver.findElement(By.cssSelector("button[routerlink*='cart']"));
//		wait.until(x -> {
//		    List<WebElement> overlays = x.findElements(By.cssSelector(".ngx-spinner-overlay"));
//		    return overlays.isEmpty() || overlays.stream().allMatch(o -> {
//		        String style = o.getDomProperty("style");
//		        return style.contains("opacity: 0") || style.contains("display: none") || style.contains("visibility: hidden");
//		    });
//		});
//		cartBtn.click();
//		
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.cart h3")));
//		List<WebElement> productNamesEle = driver.findElements(By.cssSelector("div.cart h3"));
//		List<String> productNames = productNamesEle.stream().map(name -> name.getText()).sorted().collect(Collectors.toList());
//		Assert.assertTrue(sortedItems.equals(productNames));
//		driver.findElement(By.cssSelector(".subtotal button")).click();
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

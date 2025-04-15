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

public class e2eTest {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
//		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.findElement(By.id("userEmail")).sendKeys("qwe123@daum.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qwe123!@");
		driver.findElement(By.id("login")).click();
		String loginMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-title"))).getText();
		Assert.assertEquals(loginMessage, "Login Successfully");
		
		String[] wishList = {"ZARA COAT 3", "IPHONE 13 PRO"};
		List<String> selectedItems = Arrays.asList(wishList);
		List<String> sortedItems = selectedItems.stream().sorted().collect(Collectors.toList());
				
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".container div.row div.card-body")));
		List<WebElement> itemList = driver.findElements(By.cssSelector(".container div.row div.card-body"));
		itemList.stream().filter(item -> {
			String itemName = item.findElement(By.tagName("h5")).getText();
			return selectedItems.contains(itemName);
		}).forEach(item -> {
			WebElement addCartBtn = item.findElement(By.cssSelector("button:last-of-type"));
			wait.until(x -> {
			    List<WebElement> overlays = x.findElements(By.cssSelector(".ngx-spinner-overlay"));
			    return overlays.isEmpty() || overlays.stream().allMatch(o -> {
			        String style = o.getDomProperty("style");
			        return style.contains("opacity: 0") || style.contains("display: none") || style.contains("visibility: hidden");
			    });
			});
			addCartBtn.click();
		});
		
		WebElement cartBtn = driver.findElement(By.cssSelector("button[routerlink*='cart']"));
		wait.until(x -> {
		    List<WebElement> overlays = x.findElements(By.cssSelector(".ngx-spinner-overlay"));
		    return overlays.isEmpty() || overlays.stream().allMatch(o -> {
		        String style = o.getDomProperty("style");
		        return style.contains("opacity: 0") || style.contains("display: none") || style.contains("visibility: hidden");
		    });
		});
		cartBtn.click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.cart h3")));
		List<WebElement> productNamesEle = driver.findElements(By.cssSelector("div.cart h3"));
		List<String> productNames = productNamesEle.stream().map(name -> name.getText()).sorted().collect(Collectors.toList());
		Assert.assertTrue(sortedItems.equals(productNames));
		driver.findElement(By.cssSelector(".subtotal button")).click();
		
//		driver.quit();
		
		
	}
}

package tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class e2eTest {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
				
		driver.findElement(By.id("userEmail")).sendKeys("qwe123@daum.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qwe123!@");
		driver.findElement(By.id("login")).click();
		
		String[] wishList = {"ZARA COAT 3", "IPHONE 13 PRO"};
		List<String> selectedItems = Arrays.asList(wishList);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
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
//		driver.quit();
		
		
	}
}

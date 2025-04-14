package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class e2eTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		
		driver.findElement(By.id("userEmail")).sendKeys("qwe123@daum.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qwe123!@");
		driver.findElement(By.id("login")).click();
		
		
	}
}

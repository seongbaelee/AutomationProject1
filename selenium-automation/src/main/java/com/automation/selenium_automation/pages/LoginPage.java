package com.automation.selenium_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.selenium_automation.utils.Utils;

public class LoginPage extends Utils {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public HomePage login(String id, String password) {
		driver.findElement(By.id("userEmail")).sendKeys(id);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		HomePage homePage = new HomePage(driver);
		return homePage;
	}
	
	public void gotoLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

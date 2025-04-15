package com.automation.selenium_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.automation.selenium_automation.base.Base;

public class LoginPage extends Base {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login(String id, String password) {
		driver.findElement(By.id("userEmail")).sendKeys(id);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
	}
	
	public void gotoLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

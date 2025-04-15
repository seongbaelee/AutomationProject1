package com.automation.selenium_automation.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	WebDriverWait wait;
	WebDriver driver;
	
	public Utils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public WebElement elementToBeVisibleByLocator(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}

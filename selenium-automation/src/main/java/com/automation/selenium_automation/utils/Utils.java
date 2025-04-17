package com.automation.selenium_automation.utils;

import java.time.Duration;
import java.util.List;

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

	public WebElement waitElementToAppearByLocator(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public Boolean waitElementToDisapearByLocator(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public List<WebElement> waitElementAllToAppearByLocator(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement waitElementToBeClickableByLocator(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void waitElementToAppearAndDisappear(By locator) {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
}

package com.automation.selenium_automation.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	WebDriverWait wait;
	WebDriver driver;

	public Utils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public static List<HashMap<String,Object>> getJsonDataToMap(String filePath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), "UTF-8");
		
		//string to HashMap (Jackson Databind dependency)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>() {});
		return data;
	}
	
}

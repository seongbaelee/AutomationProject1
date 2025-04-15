package com.automation.selenium_automation.pages;

import org.openqa.selenium.WebDriver;

import com.automation.selenium_automation.utils.Utils;

public class HomePage extends Utils {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}

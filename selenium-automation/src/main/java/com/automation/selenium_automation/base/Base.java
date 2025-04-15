package com.automation.selenium_automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.selenium_automation.pages.LoginPage;

public class Base {
	public WebDriver driver;
	public LoginPage loginPage;
	
	public WebDriver initializeDriver() {
		String browserName = "Chrome";
		if (browserName == "Chrome") {
			driver = new ChromeDriver();
		}

		if (browserName == "firefox") {
			driver = new FirefoxDriver();
		}

		if (browserName == "edge") {
			driver = new EdgeDriver();
		}
		
		return driver;
	}
	
	@BeforeMethod
	public LoginPage launchApp() {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.gotoLoginPage();
		return loginPage;
	}
	
	@AfterMethod
	public void quitWebsite() {
//		driver.quit();
	}
}

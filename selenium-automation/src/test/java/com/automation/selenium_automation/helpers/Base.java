package com.automation.selenium_automation.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\automation\\selenium_automation\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

//		String browserName = "Chrome";
//		if (browserName == "Chrome") {
//			driver = new ChromeDriver();
//		}
//
//		if (browserName == "firefox") {
//			driver = new FirefoxDriver();
//		}
//
//		if (browserName == "edge") {
//			driver = new EdgeDriver();
//		}

		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}

		if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}

		if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApp() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.gotoLoginPage();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void quitWebsite() {
		driver.quit();
	}
}

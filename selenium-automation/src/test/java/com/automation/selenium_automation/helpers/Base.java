package com.automation.selenium_automation.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.selenium_automation.pages.LoginPage;

public class Base {
	public WebDriver driver;
	public LoginPage loginPage;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\automation\\selenium_automation\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		
		if (browserName.contains("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("--headless=new");
			}
			options.addArguments("--window-size=1440,900");
			driver = new ChromeDriver(options);
		}

		if (browserName.equalsIgnoreCase("Firefox")) {
			FirefoxOptions options = new FirefoxOptions();
		    options.addArguments("--width=1440", "--height=900");
			driver = new FirefoxDriver(options);
		}

		if (browserName.equalsIgnoreCase("Edge")) {
			EdgeOptions options = new EdgeOptions();
		    options.addArguments("start-maximized"); 
		    options.addArguments("--window-size=1440,900"); 
			driver = new EdgeDriver(options);
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

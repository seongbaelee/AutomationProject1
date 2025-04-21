package com.automation.selenium_automation.utils;

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
//	public LoginPage loginPage;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public ThreadLocal<LoginPage> tlLoginPage = new ThreadLocal<>();


	public void initializeDriver() throws IOException {
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
			WebDriver driver = new ChromeDriver(options);
			tlDriver.set(driver);
		}

		if (browserName.equalsIgnoreCase("Firefox")) {
			FirefoxOptions options = new FirefoxOptions();
		    options.addArguments("--width=1440", "--height=900");
			WebDriver driver = new FirefoxDriver(options);
			tlDriver.set(driver);

		}

		if (browserName.equalsIgnoreCase("Edge")) {
			EdgeOptions options = new EdgeOptions();
		    options.addArguments("start-maximized"); 
		    options.addArguments("--window-size=1440,900"); 
			WebDriver driver = new EdgeDriver(options);
			tlDriver.set(driver);
		}
		
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApp() throws IOException {
		initializeDriver();
		System.out.println(tlDriver.get()+ "launchapp");
		LoginPage loginPage = new LoginPage(tlDriver.get());
		tlLoginPage.set(loginPage);
		tlLoginPage.get().gotoLoginPage();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		tlDriver.get().quit();
		tlDriver.remove();
	}
}

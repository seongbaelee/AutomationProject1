package com.automation.selenium_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium_automation.utils.Utils;

public class LoginPage extends Utils {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement emailEle;
	
	@FindBy(id="userPassword")
	WebElement userPasswordEle;
	
	@FindBy(id="login")
	WebElement loginEle;
	
	By success = By.cssSelector(".toast-title");
	By error = By.cssSelector(".toast-error");
	
	public Object[] validLogin(String id, String password) {
		emailEle.sendKeys(id);
		userPasswordEle.sendKeys(password);
		loginEle.click();
		String loginMessage = waitElementToAppearByLocator(success).getText();
		waitElementToDisapearByLocator(success);
		HomePage homePage = new HomePage(driver);
		return new Object[] {loginMessage, homePage};
	}
	
	public String invalidLogin(String id, String password) {
		emailEle.sendKeys(id);
		userPasswordEle.sendKeys(password);
		loginEle.click();
		String InvalidMessage = waitElementToAppearByLocator(error).getText();
		return InvalidMessage;
	}
	
	public void gotoLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

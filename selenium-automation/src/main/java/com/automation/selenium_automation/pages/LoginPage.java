package com.automation.selenium_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium_automation.utils.Base;
import com.automation.selenium_automation.utils.Utils;

public class LoginPage extends Utils {
	WebDriver driver;
	
	public LoginPage(WebDriver tlDriver) {
		super(Base.tlDriver.get());
		this.driver = tlDriver;
		System.out.println(tlDriver + "constructor");
		PageFactory.initElements(tlDriver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement emailEle;
	
	@FindBy(id="userPassword")
	WebElement userPasswordEle;
	
	@FindBy(id="login")
	WebElement loginEle;
	
	@FindBy(css=".invalid-feedback div")
	WebElement emailErrorEle;
	
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
		System.out.println(driver);
		String InvalidMessage = waitElementToAppearByLocator(error).getText();
		return InvalidMessage;
	}
	
	public String invalidEmail(String email, String password) {
		emailEle.sendKeys(email);
		userPasswordEle.sendKeys(password);
		loginEle.click();
		System.out.println(driver);

		String InvalidMessage = emailErrorEle.getText();
		return InvalidMessage;
	}
	
	public void gotoLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

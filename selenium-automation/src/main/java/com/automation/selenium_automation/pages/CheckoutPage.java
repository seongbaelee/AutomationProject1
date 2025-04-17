package com.automation.selenium_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium_automation.utils.Utils;

public class CheckoutPage extends Utils {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".user__address input")
	WebElement countryBtnEle;
	
	@FindBy(css =".action__submit")
	WebElement orderBtn;
	
	By countryModalLocator = By.cssSelector(".list-group");
		
	public String selectCountry(String initLetters, String targetCountry) {
		countryBtnEle.sendKeys(initLetters);
		waitElementToBeVisibleByLocator(countryModalLocator);
		driver.findElement(By.xpath("//span[contains(text() , '" + targetCountry + "')]")).click();
		String selectedCountry = countryBtnEle.getDomProperty("value");
		return selectedCountry;
	}
	
	public ConfirmationPage clickOrderBtn() {
		orderBtn.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
}

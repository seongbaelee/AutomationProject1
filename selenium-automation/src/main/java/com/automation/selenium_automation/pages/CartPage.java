package com.automation.selenium_automation.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium_automation.utils.Utils;

public class CartPage extends Utils{
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.cart h3")
	List<WebElement> productTitlesEle;
	
	@FindBy(css=".subtotal button")
	WebElement checkoutBtnEle;
	
	By ProductTitlesLocator = By.cssSelector("div.cart h3");
	By checkoutLocator = By.cssSelector(".subtotal button");
		
	public List<String> getProductTitles() {
		waitElementAllToAppearByLocator(ProductTitlesLocator);
		List<String> productTitles = productTitlesEle.stream().map(name -> name.getText()).sorted().collect(Collectors.toList());
		return productTitles;
	}
	
	public CheckoutPage clickCheckOut() throws InterruptedException {
		scrollToShowElement(checkoutBtnEle);
		checkoutBtnEle.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
	
}

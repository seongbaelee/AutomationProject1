package com.automation.selenium_automation.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium_automation.utils.Utils;

public class OrdersPage extends Utils {
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tbody tr td:nth-of-type(2)")
	List<WebElement> productNamesEle;
	
	public List<String> getOrdersProduct(int size) {
		waitElementsToAppearByElement(productNamesEle);
		List<String> productNames = productNamesEle.stream().limit(size).map(ele -> ele.getText()).sorted()
				.collect(Collectors.toList());
		return productNames;
	}
}

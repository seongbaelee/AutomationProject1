package com.automation.selenium_automation.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium_automation.utils.Utils;

public class ConfirmationPage extends Utils {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationTextEle;
	
	@FindBy(css=".line-item .m-3 .title")
	List<WebElement> productTitlesEle;
	
	By confirmationTextLocator = By.cssSelector(".hero-primary");
	By productTitleLocator = By.cssSelector(".line-item .m-3 .title");
	public String getConfirmationText() {
		waitElementToAppearByLocator(confirmationTextLocator);
		return confirmationTextEle.getText();
	}
	
	public List<String> getProductTitles() {
		waitElementAllToAppearByLocator(productTitleLocator);
		List<String> productTitles = productTitlesEle.stream()
				.map(titleEle -> titleEle.getText()).sorted().collect(Collectors.toList());
		return productTitles;
	}
}

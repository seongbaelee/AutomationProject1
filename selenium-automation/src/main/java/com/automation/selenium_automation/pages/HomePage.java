package com.automation.selenium_automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium_automation.utils.Utils;

public class HomePage extends Utils {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".container div.row div.card-body")
	List<WebElement> productsEle;
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartBtnEle;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement ordersBtnEle;
	
	By productsContentLocator = By.cssSelector(".container div.row div.card-body");
	By addBtnLocator = By.cssSelector("button:last-of-type");
	By overlayLocator = By.cssSelector(".ng-animating");
	By toastLocator = By.cssSelector(".toast-success");

	public void addItemsToCart (List<String> selectedItems) {
			waitElementAllToAppearByLocator(productsContentLocator);
			productsEle.stream().filter(productEle -> {
			String productName = productEle.findElement(By.tagName("h5")).getText();
			return selectedItems.contains(productName);
		}).forEach(productEle -> {
			WebElement addCartBtn = productEle.findElement(addBtnLocator);
			addCartBtn.click();
//			waitElementToDisapearByLocator(overlayLocator);
			waitElementToAppearAndDisappear(toastLocator);
		});
	}
	
	public CartPage clickToCart() {
		cartBtnEle.click();
	    CartPage cartPage = new CartPage(driver);
	    return cartPage;
	}

	public OrdersPage clickToOrders() {
		ordersBtnEle.click();
	    OrdersPage orderspage = new OrdersPage(driver);
	    return orderspage;
	}
}

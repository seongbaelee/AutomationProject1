package com.automation.selenium_automation.errorTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.selenium_automation.helpers.Base;

public class LoginErrorTest extends Base {
	
	@Test(groups= {"ErrorHandling"})
	public void invalidAccountTest() {
		String errorMessage = loginPage.invalidLogin("qwe123@daum.com", "Qwe123!!");
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
	
	@Test
	public void NotCorrectToastMessage() {
		String errorMessage = loginPage.invalidLogin("qwe123@daum.com", "Qwe123!!");
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "Incorrect banana or password.");
	}
}

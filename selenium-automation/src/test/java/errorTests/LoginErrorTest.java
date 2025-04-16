package errorTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.selenium_automation.base.Base;

public class LoginErrorTest extends Base {
	
	@Test
	public void errorTest() {
		String errorMessage = loginPage.invalidLogin("we123@daum.com", "Qwe123!@");
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
}

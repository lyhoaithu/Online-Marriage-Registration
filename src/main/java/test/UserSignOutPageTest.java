package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.LogOutPage;
import page.UserLogInPage;

public class UserSignOutPageTest extends TestCase {
	
	@BeforeMethod(description = "Log In")
	public void logIn() {
		UserLogInPage ulp= new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulp.txtMobileNumber, "1234567890");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}
	
	@Test(description = "Validate Logging Out", groups="main")
	public void logOutSuccessfully() {
		LogOutPage lop= new LogOutPage(driver);
		clickOnElement(lop.dropdownProfile);
		clickOnElement(lop.linkTextSignOut);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("user/login"));

	}

}

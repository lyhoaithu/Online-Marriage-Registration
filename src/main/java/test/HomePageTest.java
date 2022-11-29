package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import page.HomePage;

public class HomePageTest extends TestCase {
	@Test(description="Validate Navigating to Admin LogIn Page", groups="main")
	public void navigateToAdminLogInPage() {
		HomePage homePage= new HomePage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/index.php");
		clickOnElement(homePage.linkTextAdmin);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin/login"));

	}

	@Test(description="Validate Navigating to User LogIn Page", groups="main")
	public void navigateToUserLogInPage() {
		HomePage homePage= new HomePage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/index.php");
		clickOnElement(homePage.linkTextUser);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("user/login"));

	}

}

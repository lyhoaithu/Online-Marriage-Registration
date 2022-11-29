package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Excelutils;
import page.AdminLogInPage;
import page.AdminNotificationPage;

public class AdminNotificationPageTest extends TestCase{
	
	@BeforeMethod(description = "Log In")
	public void logIn() {
		AdminLogInPage alp = new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(alp.txtUserName, "admin");
		sendKeys(alp.txtPassword, "Test@123");
		clickOnElement(alp.btnSignIn);
	}

	public AdminNotificationPage anp= new AdminNotificationPage(driver);
	public Excelutils excel= new Excelutils();
	
	@Test(description = "Validate Clicking On The Number Of Notification", groups="main")
	public void clickingOnTheNumberOfNotification() {
		clickOnElement(anp.iconBell);
		clickOnElement(anp.textNoOfNoti);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.equals("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/new-marriage-application.php"));
	}
	
	@Test(description = "Validate Clicking On The Notification Content", groups="main")
	public void clickingOnTheNotificationContent() {
		clickOnElement(anp.iconBell);
		clickOnElement(anp.textNotificationContent);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.equals("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/new-marriage-application.php"));
	}
	
	@Test(description = "Validate Clicking On The Show All", groups="main")
	public void clickingOnTheShowAll() {
		clickOnElement(anp.iconBell);
		clickOnElement(anp.linktextShowApplication);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.equals("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/all-marriage-application.php"));
	}
	
	@Test(description = "Validate Showing Notification When User Register", dependsOnGroups = "register successfully", groups="main")
	public void validateShowingInformation() throws ClassNotFoundException, SQLException {
		String regNoDB= getValueFromDatabase("SELECT RegistrationNumber from tblregistration ORDER BY ID DESC LIMIT 1;", 1).replace("[", "").replace("]", "");
		clickOnElement(anp.iconBell);
		List<WebElement> regNoList= driver.findElements(anp.textNotiRegNo);
		String notiRegNo= regNoList.get(regNoList.size()-1).getText();
		String actualNotiRegNo= notiRegNo.substring(17);
		assertEquals(actualNotiRegNo, regNoDB);
	}
}

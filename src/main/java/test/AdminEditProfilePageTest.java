package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.AdminEditProfilePage;
import page.AdminLogInPage;

public class AdminEditProfilePageTest extends TestCase {
	public AdminEditProfilePage aepp = new AdminEditProfilePage(driver);
	public Excelutils excelUtils = new Excelutils();

	@BeforeMethod(description = "Log In")
	public void logIn() {
		AdminLogInPage ulp = new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(ulp.txtUserName, "admin");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}

	@Test(description = "Validate Navigate To Edit Profile Page", groups="main")
	public void validateNavigateToEditProfilePage() {
		clickOnElement(aepp.dropdownAdmin);
		clickOnElement(aepp.iconEditProfile);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("admin-profile"));
	}

	@Test(description = "Validate Read Only Fields", groups="main")
	public void validateReadOnlyFields() {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/admin-profile.php");
		String actualResult01 = driver.findElement(aepp.txtRegistionDate).getAttribute("readonly");
		String actualResult02 = driver.findElement(aepp.txtUserName).getAttribute("readonly");
		assertEquals(actualResult01, "true");
		assertEquals(actualResult02, "true");
	}

	@DataProvider(name = "Admin Edit Profile Successfully")
	public String[][] successfulData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminEditProfileSuccessfully");
		return data;
	}

	@Test(description = "Validate Edit Successfully", dataProvider = "Admin Edit Profile Successfully", groups="main")
	public void validateEditSuccessfully(String adminName, String phoneNo, String email, String message) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/admin-profile.php");
		sendKeysWithAttributeAvailable(aepp.txtName, adminName);
		sendKeysWithAttributeAvailable(aepp.phoneNo, phoneNo);
		sendKeysWithAttributeAvailable(aepp.email, email);
		clickOnElement(aepp.btnSubmit);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, message);

	}

	@DataProvider(name = "Admin Edit Profile Invalid Data", indices = { 0, 1, 2, 3, 4, 5, 6, 7, 8 })
	public String[][] invalidData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminEditProfileFail");
		return data;
	}

	@Test(description = "Validate Edit Fail", dataProvider = "Admin Edit Profile Invalid Data", groups="validation")
	public void validateEditFail(String adminName, String phoneNo, String email, String message) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/admin-profile.php");
		sendKeysWithAttributeAvailable(aepp.txtName, adminName);
		sendKeysWithAttributeAvailable(aepp.phoneNo, phoneNo);
		sendKeysWithAttributeAvailable(aepp.email, email);
		clickOnElement(aepp.btnSubmit);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, message);
	}

	@DataProvider(name = "Admin Edit Profile Blank Fields", indices = { 9, 10, 11 })
	public String[][] blankFieldData() throws IOException {
		String[][] data = excelUtils.getDataFromExcel(dataFilePath, "AdminEditProfileFail");
		return data;
	}

	@Test(description = "Validate Edit Successfully", dataProvider = "Admin Edit Profile Blank Fields", groups="validation")
	public void validateBlankFields(String adminName, String phoneNo, String email, String message) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/admin-profile.php");
		sendKeysWithAttributeAvailable(aepp.txtName, adminName);
		sendKeysWithAttributeAvailable(aepp.phoneNo, phoneNo);
		sendKeysWithAttributeAvailable(aepp.email, email);
		clickOnElement(aepp.btnSubmit);
		By[] elements = { aepp.txtName, aepp.phoneNo, aepp.email };
		String[] data = { adminName, phoneNo, email, message };
		getHtml5ValidationFromASeriesOfFields(elements, data, message);
	}
}
